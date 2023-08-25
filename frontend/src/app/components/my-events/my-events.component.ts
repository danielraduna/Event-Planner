import {Component, OnInit} from '@angular/core';
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";
import {EventRequest} from "../../entities/EventRequest";
import {EventRequestService} from "../../services/event-request.service";

@Component({
  selector: 'app-my-events',
  templateUrl: './my-events.component.html',
  styleUrls: ['./my-events.component.scss']
})
export class MyEventsComponent implements OnInit{
  user!: User;
  events?: Event[] = [];
  eventsRequests?: EventRequest[] = [];

  constructor(private userService: UserService,
              private eventService: EventService,
              private eventRequestService: EventRequestService) { }


  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem("user")!);
    this.eventService.getEventsByUser(this.user?.id!).subscribe(data => {
      if(data.body) {
        this.events = data.body;
      }
    });

    this.eventRequestService.getPendingEventRequestsByUserId(this.user?.id!).subscribe(data => {
      if(data.body) {
        this.eventsRequests = data.body;
      }
    });
  }

  updateEventRequests(): void {
    this.eventRequestService.getPendingEventRequestsByUserId(this.user.id!)
      .subscribe(data => {
        this.eventsRequests = data.body!;
        this.updateEvents();
      });
  }

  updateEvents(): void {
    this.eventService.getEventsByUser(this.user?.id!).subscribe(data => {
      if(data.body) {
        this.events = data.body;
      }
    });
  }
}
