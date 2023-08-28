import {Component, OnInit} from '@angular/core';
import {Poll} from "../../entities/poll";
import {ActivatedRoute} from "@angular/router";
import {PollService} from "../../services/poll.service";
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";

@Component({
  selector: 'app-event-polls',
  templateUrl: './event-polls.component.html',
  styleUrls: ['./event-polls.component.scss']
})
export class EventPollsComponent implements OnInit {
  polls: Poll[] = [];
  eventId: number = 0;
  event?: Event;
  createPollForm?: FormGroup;

  pollTypes: any[] = [
    {label: 'Single Choice', value: 'SINGLE_CHOICE'},
    {label: 'Multiple Choice', value: 'MULTIPLE_CHOICE'}
  ];
  constructor(private route: ActivatedRoute,
              private pollService: PollService,
              private fb: FormBuilder,
              private eventService: EventService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.eventId = +params.get('eventId')!; // "+" transformă string-ul în număr
      this.eventService.getEventById(this.eventId).subscribe(data => {
        this.event = data.body!;
      })
      this.getPollsForEvent();
    });

    this.createPollForm = this.fb.group({
      question: ['', Validators.required],
      pollType: ['', Validators.required],
      options: this.fb.array([this.createOption()])
    });
  }

  getPollsForEvent() {
    this.pollService.getPollsByEventId(this.eventId).subscribe(data => {
      this.polls = data;
    });
  }

  get pollOptions() {
    return this.createPollForm!.get('options') as FormArray;
  }

  createOption(): FormControl {
    return this.fb.control('', Validators.required);
  }

  addPollOption() {
    this.pollOptions.push(this.createOption());
  }

  onCreatePoll() {
    if (this.createPollForm!.valid) {
      const newPoll = {
        ...this.createPollForm!.value,
        event: this.event
      };
      this.pollService.createPoll(newPoll).subscribe(response => {
        console.log(response);
        this.createPollForm!.reset();
        this.getPollsForEvent();
      });
    }
  }






}
