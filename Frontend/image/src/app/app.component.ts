import {Image} from "./objects/image";
import {Component, OnInit} from '@angular/core';
import {ImageService} from "./services/image.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Tag} from "./objects/tag";
import {Router} from "@angular/router";
import '@cds/core/icon/register.js';
import { ClarityIcons, userIcon } from '@cds/core/icon';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ics';
  // @ts-ignore
  tags: Tag[] = [];

  constructor(private imageService: ImageService, private router: Router) {
  }

  ngOnInit(): void {
  }
  changeActiveAnalyse(){
    const analyseBtn=document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn=document.querySelectorAll<HTMLElement>('#gallery')[0];
    if(!analyseBtn.classList.contains('active'))
 {
      analyseBtn.classList.add('active');
      galleryBtn.classList.remove('active');
    }

  }

  changeActiveGallery(){
    const analyseBtn=document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn=document.querySelectorAll<HTMLElement>('#gallery')[0];
    if(!galleryBtn.classList.contains('active'))
   {
      analyseBtn.classList.remove('active');
      galleryBtn.classList.add('active');
    }

  }

}
