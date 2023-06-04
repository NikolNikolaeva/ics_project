import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Image} from "../objects/image";
import {environment} from "../environments/environment";
import {Tag} from "../objects/tag";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private apiServerUrl = environment.apiImagesUrl;

  constructor(private http: HttpClient) {
  }

  public getImages() {
    console.log(this.apiServerUrl);
    return this.http.get<Image[]>(`${this.apiServerUrl}`);
  }

  public getImagesByTag(tags: string[]): Observable<Image[]> {
    return this.http.get<Image[]>(`${this.apiServerUrl}?tags=${tags}`);
  }

  public addImage(imageUrl: string): Observable<Image> {
    return this.http.post<Image>(`${this.apiServerUrl}`, imageUrl);
  }

  public deleteImage(imageId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/delete/${imageId}`);
  }
}
