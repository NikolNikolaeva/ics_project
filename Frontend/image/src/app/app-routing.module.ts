import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {GalleryComponent} from "./gallery/gallery.component";

const routes: Routes = [];

@NgModule({
  exports: [RouterModule],
  imports: [
  //  RouterModule.forRoot([{path: 'analyse', component: AppComponent},
  //    {path: 'gallery', component: GalleryComponent},]
   // )
  ]
})
export class AppRoutingModule { }
