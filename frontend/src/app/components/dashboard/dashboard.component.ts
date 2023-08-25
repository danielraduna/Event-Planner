import {Component, OnInit} from '@angular/core';
import {User} from "../../entities/user";
import {Event} from "../../entities/event";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  user?: User;
  events?: Event[] = [];

  constructor(private userService: UserService,
              private eventService: EventService) { }

  ngOnInit(): void {
    let navbar = document.querySelector('app-navbar') as HTMLElement;
    navbar.style.display = "block";

    this.user = JSON.parse(localStorage.getItem("user")!);
    this.eventService.getEventsByUser(this.user?.id!).subscribe(data => {
      if(data.body) {
        this.events = data.body;
      }
    });
  }
}
