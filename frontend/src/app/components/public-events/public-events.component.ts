import {Component, OnInit} from '@angular/core';
import {Event} from "../../entities/event";
import {EventService} from "../../services/event.service";

@Component({
  selector: 'app-public-events',
  templateUrl: './public-events.component.html',
  styleUrls: ['./public-events.component.scss']
})
export class PublicEventsComponent implements OnInit{
  events?: Event[] = []
  constructor(private eventService: EventService) {
  }
  ngOnInit(): void {
    this.eventService.getPublicEvents().subscribe(data => {
      this.events = data;
    });
  }

  updateEvents(): void {
    this.eventService.getPublicEvents().subscribe(data => {
      if (data) {
        this.events = data;
      }
    });
  }
}
