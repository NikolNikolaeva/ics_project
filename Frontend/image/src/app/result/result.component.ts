import {Component, OnInit} from '@angular/core';
import {ImageService} from "../services/image.service";
import {Image} from "../objects/image";
import {ActivatedRoute, Router} from "@angular/router";
import '@cds/core/icon/register.js';
import { ClarityIcons, thumbsUpIcon, thumbsDownIcon, talkBubblesIcon, downloadIcon } from '@cds/core/icon';

ClarityIcons.addIcons(thumbsUpIcon);
ClarityIcons.addIcons(thumbsDownIcon);
ClarityIcons.addIcons(talkBubblesIcon);
ClarityIcons.addIcons(downloadIcon);

@Component({
  selector: 'app-result',
  templateUrl: 'result.component.html',
  styleUrls: ['result.component.scss']
})
export class ResultComponent implements OnInit {
  // @ts-ignore
  imageToAnalyse: Image;

  constructor(private imageService: ImageService, private route: ActivatedRoute,private router: Router) {
  }

  navigateToResultView(tag: string) {
    this.router.navigateByUrl(`/images/${tag}`);
  }

  ngOnInit(): void {
    let imageId = this.route.snapshot.params['id'];
    this.getImageById(imageId);
  }

  getImageById(id: number) {
    this.imageService.getImageById(id).subscribe(
      (image) => {
        this.imageToAnalyse = image;
      });
  }

  likeImageWithId(id: number | undefined) {
    if (id !== undefined) {
      this.imageService.getImageById(id).subscribe(
        (image) => {
          image.likes += 1;
          this.imageService.updateImage(image).subscribe((updatedImage: Image) => this.imageToAnalyse = updatedImage)
        },
        (error) => {
          console.error('Error liking the image:', error);
        }
      );
    } else {
      console.error('Image ID is undefined');
    }
  }

  dislikeImageWithId(id: number | undefined) {
    if (id !== undefined) {
      this.imageService.getImageById(id).subscribe(
        (image) => {
          image.dislikes += 1;
          this.imageService.updateImage(image).subscribe((updatedImage: Image) => this.imageToAnalyse = updatedImage)
        },
        (error) => {
          console.error('Error unliking the image:', error);
        }
      );
    } else {
      console.error('Image ID is undefined');
    }
  }

  //Not working as expected at the moment
  downloadImage(imageUrl: string | undefined) {
    if (imageUrl) {
      const link = document.createElement('a');
      link.href = imageUrl;
      link.download = imageUrl.split('/').pop() ?? 'downloaded_image';
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    } else {
      console.error('Image URL is not available');
    }
  }
}
