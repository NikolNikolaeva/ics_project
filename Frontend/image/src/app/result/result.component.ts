import { Component, OnInit } from '@angular/core';
import { ImageService } from "../services/image.service";
import { UserService } from "../services/user.service";
import { Image } from "../objects/image";
import { ActivatedRoute, Router } from "@angular/router";
import '@cds/core/icon/register.js';
import { ClarityIcons, thumbsUpIcon, thumbsDownIcon, talkBubblesIcon, downloadIcon, trashIcon } from '@cds/core/icon';
import {User} from "../objects/user";
import {switchMap} from "rxjs";

ClarityIcons.addIcons(thumbsUpIcon, thumbsDownIcon, talkBubblesIcon, downloadIcon, trashIcon);

@Component({
  selector: 'app-result',
  templateUrl: 'result.component.html',
  styleUrls: ['result.component.scss']
})
export class ResultComponent implements OnInit {
  imageToAnalyse: Image | undefined;
  isCommentPopupVisible = false;
  newComment = '';
  private lastCommentId = 1;
  isDeletePopupVisible: boolean = false;
  imageUrlToDelete: string | undefined;
  currentUser: string = '';

  constructor(
    private imageService: ImageService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const imageId = this.route.snapshot.params['id'];
    this.getImageById(imageId);
    this.getCurrentUser();
  }

  getCurrentUser(): void {
    const userToken = localStorage.getItem('userToken') || '';
    this.userService.getUserById(Number.parseInt(userToken)).subscribe(
      (user: User) => {
        this.currentUser = user.username;
      }
    );
  }

  getImageById(id: number): void {
    this.imageService.getImageById(id).subscribe(
      (image) => {
        this.imageToAnalyse = image;
      },
      (error) => {
        console.error('Error fetching image:', error);
      }
    );
  }

  navigateToResultView(tag: string): void {
    this.router.navigateByUrl(`/images/${tag}`);
  }

  likeImageWithId(id: number | undefined): void {
    if (id !== undefined) {
      this.imageService.getImageById(id).subscribe(
        (image) => {
          let usersRated = image.usersRated ? image.usersRated.split(',') : [];
          if (!usersRated.includes(this.currentUser)) {
            usersRated.push(this.currentUser);
            image.usersRated = usersRated.join(',');
            image.likes += 1;
            console.log(image)
            this.imageService.updateImage(image).subscribe(
              (updatedImage: Image) => this.imageToAnalyse = updatedImage,
              (error) => console.error('Error updating image:', error)
            );
          }
        },
        (error) => {
          console.error('Error liking the image:', error);
        }
      );
    } else {
      console.error('Image ID is undefined');
    }
  }

  dislikeImageWithId(id: number | undefined): void {
    if (id === undefined) {
      console.error('Image ID is undefined');
      return;
    }

    this.imageService.getImageById(id).subscribe(
      (image) => {
        const usersRated = image.usersRated ? image.usersRated.split(',').filter(Boolean) : [];
        if (!usersRated.includes(this.currentUser)) {
          image.dislikes += 1;
          usersRated.push(this.currentUser);
          image.usersRated = usersRated.join(',');
          
          this.imageService.updateImage(image).subscribe(
            (updatedImage: Image) => {
              this.imageToAnalyse = updatedImage;
            },
            (updateError) => {
              console.error('Error updating image:', updateError);
            }
          );
        } else {
          console.log('User has already rated this image.');
        }
      },
      (fetchError) => {
        console.error('Error fetching image:', fetchError);
      }
    );
  }

  openCommentPopup(): void {
    this.isCommentPopupVisible = true;
  }

  saveComment(id: number | undefined): void {
    if (!this.newComment.trim()) {
      alert('Comment cannot be empty.');
      return;
    }

    const currentTime = new Date();

    if (id === undefined) {
      console.error('Image ID is undefined');
      return;
    }

    this.imageService.getImageById(id).pipe(
      switchMap((image) => {
        if (!image.comments) {
          image.comments = [];
        }

        this.lastCommentId += 1;

        image.comments.push({
          id: this.lastCommentId,
          commentText: this.newComment.trim(),
          author: this.currentUser,
          date: currentTime,
        });

        return this.imageService.updateImage(image);
      })
    ).subscribe(
      (updatedImage: Image) => {
        this.imageToAnalyse = updatedImage;
        this.dismissCommentPopup();
      },
      (error) => {
        console.error('Error saving comment:', error);
      }
    );
  }

  dismissCommentPopup(): void {
    this.isCommentPopupVisible = false;
    this.newComment = '';
  }

  confirmDelete(imageUrl: string | undefined): void {
    if (imageUrl) {
      this.imageUrlToDelete = imageUrl;
      this.isDeletePopupVisible = true;
    }
  }

  deleteImage(id: number | undefined): void {
    if (id) {
      this.imageService.deleteImage(id).subscribe(
        () => {
          this.router.navigateByUrl(`/images`)
        },
        (error) => {
          console.error('Error deleting the image:', error);
        }
      );
    } else {
      console.error('Image ID is undefined');
    }
  }

  dismissDeletePopup(): void {
    this.isDeletePopupVisible = false;
    this.imageUrlToDelete = undefined;
  }
}
