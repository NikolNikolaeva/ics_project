import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Tag} from "../objects/tag";
import {TagService} from "../services/tag.service";
import {ImageService} from "../services/image.service";
import {Image} from "../objects/image";
import {tagsIconName} from "@cds/core/icon";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-gallery',
  templateUrl: 'gallery.component.html',
  styleUrls: ['gallery.component.scss']
})
export class GalleryComponent implements OnInit {
  //title = 'ics';
  tags: Tag[] = [];
  images: Image[] = [];
  imagesAll: Image[] = [];
  @Input('ngModel')
  selection: any[] = [];
  tagsNames: string[] = [];
  tagParam: string[] = [];

  constructor(private tagService: TagService,
              private imageService: ImageService, private router: Router, private route: ActivatedRoute) {
  }

  getSelectedTags(): string[] {
    return this.selection.map(selected => selected['name']);
  }

  ngOnInit() {

    let tag = this.route.snapshot.params['tag'];
    if(tag!=null) {
      this.tagParam.push(tag);
      this.getImagesByTags();
    }
    else {
      this.getAllImages();
      this.imagesAll = this.images;
    }
    this.getTags();
    this.tagsNames.sort();

  }

  navigateToResultView(id: number) {
    this.router.navigateByUrl(`/result/${id}`);
  }

  private getImages(){
    this.imageService.getImagesByTag(this.tagsNames).subscribe(
      (imagesByTags) => {
        this.images = imagesByTags;
      }
    );
  }

  getTags() {
    this.tagService.getTags().subscribe(
      (tags) => {
        this.tags = tags.sort();
      }
    );
  }

  getAllImages() {
    if (this.imagesAll.length == 0) {
      this.imageService.getImages().subscribe(
        (images) => {
          this.images = images;
        }
      );
    } else {
      this.images = this.imagesAll;
    }
  }
  getImagesByTags() {
    let container = document.querySelectorAll<HTMLElement>('ng-container')[0];
    this.tagsNames = this.getSelectedTags();
    if (this.tagParam.length == 0) {

      if (this.tagsNames.length != 0) {
        this.getImages();
      } else {
        //this.getAllImages();
      }
    } else{
      this.tagsNames = this.tagParam;
      this.getImages();
      this.tagParam.pop();
      console.log(this.tagParam)
    }
  }


}
