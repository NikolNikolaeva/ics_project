import {Component, OnInit} from '@angular/core';
import {ImageService} from "../services/image.service";
import {Tag} from "../objects/tag";
import {Image} from "../objects/image";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

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
        console.log(this.imageToAnalyse);

      });
  }
}
