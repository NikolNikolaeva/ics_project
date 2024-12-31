import {Image} from "./objects/image";
import {Component, OnInit, OnChanges, SimpleChanges} from '@angular/core';
import {ImageService} from "./services/image.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Tag} from "./objects/tag";
import {Router} from "@angular/router";
import '@cds/core/icon/register.js';
import {ClarityIcons, userIcon} from '@cds/core/icon';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ics';
  // @ts-ignore
  tags: Tag[] = [];

  constructor(private imageService: ImageService, private router: Router, public authService: AuthService) {
  }

  ngOnInit(): void {
    this.checkPageActivity();
  }

  checkPageActivity() {
    const analyseBtn = document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn = document.querySelectorAll<HTMLElement>('#gallery')[0];
    const statisticsBtn = document.querySelectorAll<HTMLElement>('#statistics')[0];
    const profileBtn = document.querySelectorAll<HTMLElement>('#profile')[0];

    if (document.URL.includes("images")) {
      analyseBtn.classList.remove('active');
      galleryBtn.classList.add('active');
      statisticsBtn.classList.remove('active');
      profileBtn.classList.remove('active');
    } else if(document.URL.includes("analyse")){
      analyseBtn.classList.add('active');
      galleryBtn.classList.remove('active');
      statisticsBtn.classList.remove('active');
      profileBtn.classList.remove('active');
    } else if(document.URL.includes("statistics")){
      analyseBtn.classList.remove('active');
      galleryBtn.classList.remove('active');
      statisticsBtn.classList.add('active');
      profileBtn.classList.remove('active');
    } else if(document.URL.includes("profile")) {
      analyseBtn.classList.remove('active');
      galleryBtn.classList.remove('active');
      statisticsBtn.classList.remove('active');
      profileBtn.classList.add('active');
    }
  }

  changeActiveAnalyse() {
    const analyseBtn = document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn = document.querySelectorAll<HTMLElement>('#gallery')[0];
    const statisticsBtn = document.querySelectorAll<HTMLElement>('#statistics')[0];
    const profileBtn = document.querySelectorAll<HTMLElement>('#profile')[0];
    if (!analyseBtn.classList.contains('active')) {
      analyseBtn.classList.add('active');
      galleryBtn.classList.remove('active');
      statisticsBtn.classList.remove('active');
      profileBtn.classList.remove('active');
    }

  }

  changeActiveGallery() {
    const analyseBtn = document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn = document.querySelectorAll<HTMLElement>('#gallery')[0];
    const statisticsBtn = document.querySelectorAll<HTMLElement>('#statistics')[0];
    const profileBtn = document.querySelectorAll<HTMLElement>('#profile')[0];
    if (!galleryBtn.classList.contains('active')) {
      analyseBtn.classList.remove('active');
      galleryBtn.classList.add('active');
      statisticsBtn.classList.remove('active');
      profileBtn.classList.remove('active');
    }
  }

  changeActiveStatistics() {
    const analyseBtn = document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn = document.querySelectorAll<HTMLElement>('#gallery')[0];
    const statisticsBtn = document.querySelectorAll<HTMLElement>('#statistics')[0];
    const profileBtn = document.querySelectorAll<HTMLElement>('#profile')[0];
    if (!statisticsBtn.classList.contains('active')) {
      analyseBtn.classList.remove('active');
      galleryBtn.classList.remove('active');
      statisticsBtn.classList.add('active');
      profileBtn.classList.remove('active');
    }
  }

  changeActiveProfile() {
    const analyseBtn = document.querySelectorAll<HTMLElement>('#analyse')[0];
    const galleryBtn = document.querySelectorAll<HTMLElement>('#gallery')[0];
    const statisticsBtn = document.querySelectorAll<HTMLElement>('#statistics')[0];
    const profileBtn = document.querySelectorAll<HTMLElement>('#profile')[0];
    if (!profileBtn.classList.contains('active')) {
      analyseBtn.classList.remove('active');
      galleryBtn.classList.remove('active');
      statisticsBtn.classList.remove('active');
      profileBtn.classList.add('active');
    }
  }
}
