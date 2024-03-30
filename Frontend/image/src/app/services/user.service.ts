import {Injectable} from "@angular/core";
import {environment} from "../environments/environment";
import {HttpClient} from "@angular/common/http";
import {User, CreateUser} from "../objects/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = environment.apiUserUrl;

  constructor(private http: HttpClient) {
  }

  public getUsers() {
    console.log(this.apiServerUrl);
    return this.http.get<User[]>(`${this.apiServerUrl}`);
  }

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/id=${id}`);
  }

  public getUserByUsername(username:string): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/username/${username}`);
  }

  public addUser(user: User): Observable<User> {
    // @ts-ignore
    return this.http.post<User>(`${this.apiServerUrl}`,user);
  }

  public getUserExistByUsername(username:string): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/${username}`);
  }
}
