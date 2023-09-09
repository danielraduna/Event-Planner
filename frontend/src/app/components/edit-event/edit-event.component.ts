import {Component, OnInit} from '@angular/core';
import {Event} from "../../entities/event";
import {ActivatedRoute, Router} from "@angular/router";
import {PollService} from "../../services/poll.service";
import {FormBuilder} from "@angular/forms";
import {EventService} from "../../services/event.service";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.scss']
})
export class EditEventComponent implements OnInit{

  event!: Event;
  eventId: number = 0;
  items!: MenuItem[];
  activeIndex: number = 0;
  type?: 'singleDay' | 'multiDay';
  dateRange?: [Date, Date] | null = null;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private pollService: PollService,
              private fb: FormBuilder,
              private eventService: EventService) {
  }

  ngOnInit(): void {
    this.items = [
      { label: 'Informatii' },
      { label: 'Invitatii' },
      { label: 'Data' },
      { label: 'Ultimele detalii' },
    ];

    this.route.paramMap.subscribe(params => {
      this.eventId = +params.get('eventId')!; // "+" transformă string-ul în număr
      this.eventService.getEventById(this.eventId).subscribe(data => {
        this.event = data.body!;
        if(this.event.stopDate !== null && this.event.stopDate !== undefined) {
          this.type = "multiDay";
          this.dateRange = [new Date(this.event.startDate), new Date(this.event.stopDate)];
        }
        else {
          this.type = "singleDay";
        }

      })
    });
  }

  next() {
    this.activeIndex++;
  }

  prev() {
    this.activeIndex--;
  }

  onActiveIndexChange(event: number) {
    this.activeIndex = event;
  }


  resetEventDetails(): void {
    this.dateRange = null;
    this.event.startTime = "";
    this.event.endTime = "";
  }

  updateEvent(): void {
    if(this.dateRange !== null) {
      this.event.startDate = this.dateRange![0];
      this.event.stopDate = this.dateRange![1];
    }
    this.eventService.updateEvent(this.event).subscribe(data => {
      this.router.navigate(['/event', this.event.id]);
    });

  }
}
