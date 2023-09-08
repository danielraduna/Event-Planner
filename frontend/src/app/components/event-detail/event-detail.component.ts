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
  showExtraDetailsInput: boolean = false;
  tempExtraDetails: string = '';
  buttonDisabled = false;

  constructor(private route: ActivatedRoute,
              private eventService: EventService
              ) { }
  ngOnInit(): void {
    this.eventId = +this.route.snapshot.paramMap.get('id')!;
    this.eventService.getEventById(this.eventId).subscribe(data => this.event = data.body!);
  }

  addExtraDetails() {
    if (this.tempExtraDetails.trim()) { // doar dacă există un detaliu valid
      if(!this.event.extraDetails) {
        this.event.extraDetails = [];

      }
      this.event.extraDetails.push(this.tempExtraDetails.trim());
      this.tempExtraDetails = '';
      this.eventService.updateEvent(this.event).subscribe();
    }
    this.showExtraDetailsInput = false;
    this.buttonDisabled = false;
  }

  showExtraDetailsInputFunction() {
    this.showExtraDetailsInput = true;
    this.buttonDisabled = true; // Dezactivați butonul
  }

}
