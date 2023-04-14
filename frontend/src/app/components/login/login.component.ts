import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";
import {LoginDTO} from "../../entities/loginDTO";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent  implements OnInit {

  registerForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl(''),
    birthday: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
  });

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private router: Router,
              private toastr: ToastrService,
              private userService: UserService) {
  }
  ngOnInit(): void {

  }


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

      this.loginForm = new FormGroup({
        username: new FormControl(''),
        password: new FormControl('')
      });
    }
    else {
      registerTab!.classList.remove("active");
      loginTab!.classList.add("active");
      login.classList.remove("invisible");
      login.classList.add("visible");
      register.classList.remove("visible");
      register.classList.add("invisible");
      title!.textContent = "WELCOME BACK!";
      this.registerForm = new FormGroup({
        firstName: new FormControl(''),
        lastName: new FormControl(''),
        username: new FormControl(''),
        email: new FormControl(''),
        phone: new FormControl(''),
        birthday: new FormControl(''),
        password: new FormControl(''),
        confirmPassword: new FormControl(''),
      });
    }
  }

  login(): void {
    if(this.loginForm.value.username && this.loginForm.value.password) {
      this.userService.login({username: this.loginForm.value.username, password: this.loginForm.value.password}).subscribe(data => {
        if(data.body!.id) {
          const authenticatedUser = data.body!;

          // Store the original, unencrypted password in the user object
          authenticatedUser.password = this.loginForm.value.password!;
          localStorage.setItem('user', JSON.stringify(authenticatedUser));
          console.log(localStorage.getItem('user'));
          this.router.navigate(['/dashboard']);
        }
        else {
          this.toastr.error("Username or password are incorrect!");
        }
      })
    }
    else {
      this.toastr.error("Completati fiecare camp!");
    }
  }

  register(): void {
    let toastrMessage: string = "";
    let firstName = document.querySelector("#firstName");
    let lastName = document.querySelector("#lastName");
    let username = document.querySelector("#username");
    let email = document.querySelector("#email");
    let phone = document.querySelector("#phone");
    let birthday = document.querySelector("#birthday");
    let password = document.querySelector("#password");
    let confirmPassword = document.querySelector("#confirmPassword");

    if (this.registerForm.value.password !== this.registerForm.value.confirmPassword) {
      toastrMessage = "Parolele nu corespund!";
    }
    if (this.registerForm.value.firstName === "") {
      toastrMessage = "Completati campurile obligatorii";
      firstName!.classList.add("dirty");
    }
    else {
      if(firstName!.classList.contains("dirty")) {
        firstName!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.lastName === "") {
      toastrMessage = "Completati campurile obligatorii";
      lastName!.classList.add("dirty");
    }
    else {
      if(lastName!.classList.contains("dirty")) {
        lastName!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.username === "") {
      toastrMessage = "Completati campurile obligatorii";
      username!.classList.add("dirty");
    }
    else {
      if(username!.classList.contains("dirty")) {
        username!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.email === "") {
      toastrMessage = "Completati campurile obligatorii";
      email!.classList.add("dirty");
    }
    else {
      if(email!.classList.contains("dirty")) {
        email!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.phone === "") {
      toastrMessage = "Completati campurile obligatorii";
      phone!.classList.add("dirty");
    }
    else {
      if(phone!.classList.contains("dirty")) {
        phone!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.birthday === "") {
      toastrMessage = "Completati campurile obligatorii";
      birthday!.classList.add("dirty");
    }
    else {
      if(birthday!.classList.contains("dirty")) {
        birthday!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.password === "") {
      toastrMessage = "Completati campurile obligatorii";
      password!.classList.add("dirty");
    }
    else {
      if(password!.classList.contains("dirty")) {
        password!.classList.remove("dirty");
      }
    }

    if (this.registerForm.value.confirmPassword === "") {
      toastrMessage = "Completati campurile obligatorii";
      confirmPassword!.classList.add("dirty");
    }
    else {
      if(confirmPassword!.classList.contains("dirty")) {
        confirmPassword!.classList.remove("dirty");
      }
    }


    if (toastrMessage !== "") {
      this.toastr.error(toastrMessage);
    }
    else {
      let user: User = {
        firstName: this.registerForm.value.firstName!,
        lastName: this.registerForm.value.lastName!,
        username: this.registerForm.value.username!,
        email: this.registerForm.value.email!,
        phone: this.registerForm.value.phone!,
        birthday: new Date(this.registerForm.value.birthday!),
        password: this.registerForm.value.password!
      }
      this.userService.createUser(user).subscribe();
    }
  }
}
