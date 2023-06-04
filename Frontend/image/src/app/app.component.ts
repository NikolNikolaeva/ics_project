import {Image} from "./objects/image";
import {Component, OnInit} from '@angular/core';
import {ImageService} from "./services/image.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Tag} from "./objects/tag";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ics';
  images: Image[] = [];
  // @ts-ignore
  imageToAnalyse: Image;
  tags:Tag[]=[];

  constructor(private imageService: ImageService) {
  }

  // getAllImages() {
  //   this.imageService.getImages().subscribe(
  //     (images) => {
  //       console.log(images);
  //       this.images = images;
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }

  getImageByUrl() {
    const inputEl = document.getElementById('url');
    // @ts-ignore
    let url=inputEl.value;
    const subscription = this.imageService.addImage(url).subscribe(
      (image) => {
        this.imageToAnalyse = image;
        this.tags=this.imageToAnalyse.tags;
        const successEl=document.querySelectorAll<HTMLElement>('.alert-success')[0];
        successEl.classList.add('active');
        const dangerEl=document.querySelectorAll<HTMLElement>('.alert-danger')[0];
        dangerEl.classList.remove('active');
      },
      (error: HttpErrorResponse) => {
        const successEl=document.querySelectorAll<HTMLElement>('.alert-success')[0];
        successEl.classList.remove('active');
        const dangerEl=document.querySelectorAll<HTMLElement>('.alert-danger')[0];
        dangerEl.classList.add('active');

          });
  }

  ngOnInit(): void {
    this.imageService.getImagesByTag(['bear']).subscribe(

    );
  }



}
