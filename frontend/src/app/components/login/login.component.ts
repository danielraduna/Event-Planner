import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  switchLoginRegister($event: any): void {
    let registerTab = document.querySelector(".reg-tab");
    let loginTab = document.querySelector(".log-tab");
    let login = document.querySelector(".login") as HTMLDivElement;
    let register = document.querySelector(".register") as HTMLDivElement;
    let title = document.querySelector("h1");
    if($event.target.attributes.id.value === "sign-in-link") {
      loginTab!.classList.remove("active");
      registerTab!.classList.add("active");
      register.classList.remove("invisible");
      register.classList.add("visible");
      login.classList.remove("visible");
      login.classList.add("invisible");
      title!.textContent = "CREATE ACCOUNT";
    }
    else {
      registerTab!.classList.remove("active");
      loginTab!.classList.add("active");
      login.classList.remove("invisible");
      login.classList.add("visible");
      register.classList.remove("visible");
      register.classList.add("invisible");
      title!.textContent = "WELCOME BACK!";
    }
  }
}
