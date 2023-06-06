import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {Tag} from "../objects/tag";
import {ImageService} from "../services/image.service";

@Component({
  selector: 'app-analyse',
  templateUrl: 'analyse.component.html',
  styleUrls: ['analyse.component.scss']
})
export class AnalyseComponent implements OnInit {
  // @ts-ignore
  imageToAnalyse: Image;

  constructor(private imageService: ImageService, private router: Router) {
  }

  ngOnInit(): void {
  }

  navigateToResultView(id:number) {
    this.router.navigateByUrl(`/result/${id}`);
  }

  getImageByUrl() {
    let inputEl = document.querySelectorAll<HTMLElement>('#url')[0];
    // @ts-ignore
    let url = inputEl.value;
    this.imageService.addImage(url).subscribe(
      (image) => {
        this.imageToAnalyse = image;
        const successEl = document.querySelectorAll<HTMLElement>('.alert-success')[0];
        successEl.classList.add('active');
        const dangerEl = document.querySelectorAll<HTMLElement>('.alert-danger')[0];
        dangerEl.classList.remove('active');
        // @ts-ignore
        inputEl.value='';
      },
      (error: HttpErrorResponse) => {
        const successEl = document.querySelectorAll<HTMLElement>('.alert-success')[0];
        successEl.classList.remove('active');
        const dangerEl = document.querySelectorAll<HTMLElement>('.alert-danger')[0];
        dangerEl.classList.add('active');
        // @ts-ignore
        inputEl.value='';
      });
  }
}
