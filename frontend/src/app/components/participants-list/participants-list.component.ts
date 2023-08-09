import {Component, OnInit} from '@angular/core';
import {User} from "../../entities/user";
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";
import {EventRequestService} from "../../services/event-request.service";
import {EventRequest} from "../../entities/EventRequest";

@Component({
  selector: 'app-participants-list',
  templateUrl: './participants-list.component.html',
  styleUrls: ['./participants-list.component.scss']
})
export class ParticipantsListComponent implements OnInit {
  event!: Event;
  users: User[] = [];
  rejectedUsers: User[] = [];
  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private eventRequestService: EventRequestService) {
  }

  ngOnInit() {
    this.getEvent();

  }

  getEvent(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.eventService.getEventById(id).subscribe(data => {
      this.event = data.body!;
      this.users = this.event.users;
      this.getRejectedUsers(id);
    });
  }

  getRejectedUsers(eventId: number): void {
    this.eventRequestService.getRejectedUsersByEventId(eventId).subscribe(
      data => {
        this.rejectedUsers = data.body!;
        console.log(this.rejectedUsers);
      }
    );
  }
}
