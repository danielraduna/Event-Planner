import { Component, OnInit } from '@angular/core';
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {

  events: Event[] = [];
  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.eventService.getAllEvents().subscribe(data => {
      if(data.body) {
        this.events = data.body!;
        console.log(this.events);
      }

    })
  }

}
