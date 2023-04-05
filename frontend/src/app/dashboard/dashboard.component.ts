import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{


  ngOnInit(): void {
    let navbar = document.querySelector('app-navbar') as HTMLElement;
    navbar.style.display = "block";
  }

}
