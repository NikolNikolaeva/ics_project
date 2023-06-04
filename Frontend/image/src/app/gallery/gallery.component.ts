import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Tag} from "../objects/tag";
import {TagService} from "../services/tag.service";
import {ImageService} from "../services/image.service";
import {Image} from "../objects/image";

@Component({
  selector: 'gallery-root',
  templateUrl: 'gallery.component.html',
  styleUrls: ['gallery.component.scss']
})
export class GalleryComponent implements OnInit {
  //title = 'ics';
  tags: Tag[]=[];
  images: Image[]=[];

  constructor(private tagService: TagService,
              private imageService:ImageService) {
  }

  ngOnInit() {
    this.getTags();
  }

  getTags() {
    this.tagService.getTags().subscribe(
      (tags) => {
        console.log(tags);
        this.tags = tags;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getAllImages() {
    this.imageService.getImages().subscribe(
      (images) => {
        console.log(images);
        this.images = images;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
