import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ClarityModule} from "@clr/angular";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {GalleryComponent} from "./gallery/gallery.component";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    AppComponent,GalleryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ClarityModule,
    HttpClientModule,
    FormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent,GalleryComponent],

})
export class AppModule { }
