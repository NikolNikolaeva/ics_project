import {NgModule} from '@angular/core';
import {provideRouter, RouterModule, Routes, withComponentInputBinding} from '@angular/router';
import {AppComponent} from "./app.component";
import {GalleryComponent} from "./gallery/gallery.component";
import {ResultComponent} from "./result/result.component";
import {AnalyseComponent} from "./analyse/analyse.component";

const routes: Routes = [
  {path: '', component: AnalyseComponent},
  {path: 'result/:id', component: ResultComponent},
  {path: 'images', component: GalleryComponent},
  {path: 'images/:tag', component: GalleryComponent},
  {path: '**', component: AnalyseComponent},
];

@NgModule({
  exports: [RouterModule],
  imports: [
    RouterModule.forRoot(routes)
  ],
})
export class AppRoutingModule {}
