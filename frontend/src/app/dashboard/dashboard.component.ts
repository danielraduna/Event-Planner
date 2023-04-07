import {Component, OnInit} from '@angular/core';
import {User} from "../entities/user";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  user?: User;

  ngOnInit(): void {
    let navbar = document.querySelector('app-navbar') as HTMLElement;
    navbar.style.display = "block";

  }



}
