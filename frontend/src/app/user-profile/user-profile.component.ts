import {Component, OnInit} from '@angular/core';
import {User} from "../entities/user";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit{
  value = 4.3;
  user?: User;

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem("user")!);
  }

}
