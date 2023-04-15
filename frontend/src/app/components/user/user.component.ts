import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../entities/user";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user?: User;
  event?: Event;
  constructor(private userService: UserService,
              private eventService: EventService) { }

  ngOnInit(): void {
    this.userService.getUserById(2).subscribe(data => {
      if(data.body) {
        this.user = data.body;

        this.eventService.getAllEvents().subscribe(res => {
          this.event = res.body![0];
        })
      }
    });
  }

  assignUserToEvent(): void {
    this.userService.assignUserToUser(1, 2).subscribe();
  }
}
