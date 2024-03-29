import { Component } from '@angular/core';
import {ClrLoadingState} from "@clr/angular";
import {CreateUser, User} from "../objects/user";
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {AuthService} from "../services/auth.service";
import * as jwt from "jsonwebtoken";
import config from "../config/index";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {

  // @ts-ignore
  user:CreateUser;
  submitBtnState: ClrLoadingState = ClrLoadingState.DEFAULT;

  constructor(private userService: UserService, private router: Router,private authService: AuthService) {
  }
  async submitDemo() {
    this.submitBtnState = ClrLoadingState.LOADING;
    setTimeout(() => this.submitBtnState = ClrLoadingState.DEFAULT, 1000);

  }
  onSubmit() {

    let username=document.querySelector<HTMLElement>("#username");
    let email=document.querySelector<HTMLElement>("#email");
    let password=document.querySelector<HTMLElement>("#password");
    let informDiv=document.getElementById("inform");

    this.submitDemo();

      // @ts-ignore
      this.userService.getUserByUsername(username.value).subscribe({
        next: (user: User) => {
          // Assuming the 'user' object has a 'password' property to compare with
          // @ts-ignore
          if(user!=null){
            // @ts-ignore
            informDiv.innerHTML=`<p>User already exists</p>`;
          } else {
            // Perform login and navigate
            this.router.navigateByUrl(`/`)
              .then(r => {
                // @ts-ignore
                this.authService.register(username.value,email.value,password.value)
              })
          }
        }
      })
  }
}
