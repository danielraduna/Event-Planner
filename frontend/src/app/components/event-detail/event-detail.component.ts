import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent implements OnInit{
  eventId!: number;
  event!: Event;

  constructor(private route: ActivatedRoute,
              private eventService: EventService
              ) { }
  ngOnInit(): void {
    this.eventId = +this.route.snapshot.paramMap.get('id')!;
    this.eventService.getEventById(this.eventId).subscribe(data => this.event = data.body!);
  }

}
