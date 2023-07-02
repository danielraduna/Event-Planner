import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent implements OnInit{
  eventId!: number;

  constructor(private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.eventId = +this.route.snapshot.paramMap.get('id')!;
  }

}
