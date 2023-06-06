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
  imagesAll:Image[]=[];
  @Input('ngModel')
  selection: any[] = [];
  tagsNames: string[] = [];

  constructor(private tagService: TagService,
              private imageService: ImageService, private router: Router,private route: ActivatedRoute) {
  }

  getSelectedTags(): string[] {
    return this.selection.map(selected => selected['name']);
  }

  ngOnInit() {
    let tag = this.route.snapshot.params['name'];
    let tags=new Array();
    tags.push(tag);
    this.getImagesByTags();
    this.getAllImages();
    this.getTags();
    this.tagsNames.sort();
    this.imagesAll=this.images;
  }

  navigateToResultView(id: number) {
    this.router.navigateByUrl(`/result/${id}`);
  }

  getTags() {
    this.tagService.getTags().subscribe(
      (tags) => {
        this.tags = tags;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getAllImages() {
    if(this.imagesAll.length==0) {
      this.imageService.getImages().subscribe(
        (images) => {
          this.images = images;
        }
      );
    }
    else{
      this.images=this.imagesAll;
    }
  }

  getImagesByTags() {
    let container = document.querySelectorAll<HTMLElement>('ng-container')[0];
    this.tagsNames = this.getSelectedTags();
    if (this.tagsNames.length != 0) {
      this.imageService.getImagesByTag(this.tagsNames.sort()).subscribe(
        (imagesByTags) => {
          this.images = imagesByTags;
        }
      )
    }
    else{
      this.getAllImages();
    }
  }


}
