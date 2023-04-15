import {Component, OnInit} from '@angular/core';
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";

@Component({
  selector: 'app-my-events',
  templateUrl: './my-events.component.html',
  styleUrls: ['./my-events.component.scss']
})
export class MyEventsComponent implements OnInit{
  user?: User;
  events?: Event[] = [];
  constructor(private userService: UserService,
              private eventService: EventService) { }


  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem("user")!);
    this.eventService.getEventsByUser(this.user?.id!).subscribe(data => {
      if(data.body) {
        this.events = data.body;
      }
    })
  }
}
