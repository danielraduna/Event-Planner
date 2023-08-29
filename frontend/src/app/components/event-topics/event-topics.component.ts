import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventService} from "../../services/event.service";
import {Event} from "../../entities/event";
import {Topic} from "../../entities/Topic";
import {TopicService} from "../../services/topic.service";

@Component({
  selector: 'app-event-topics',
  templateUrl: './event-topics.component.html',
  styleUrls: ['./event-topics.component.scss']
})
export class EventTopicsComponent implements OnInit{

  topics: Topic[] = [];
  eventId: number = 0;
  event?: Event;
  createTopicForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private topicService: TopicService,
              private fb: FormBuilder,
              private eventService: EventService) {
    this.createTopicForm = new FormGroup({
      'title': new FormControl(null, Validators.required)
    });
  }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.eventId = +params.get('eventId')!; // "+" transformă string-ul în număr
      this.eventService.getEventById(this.eventId).subscribe(data => {
        this.event = data.body!;
      })
      this.getTopicsForEvent();
    })
  }

  getTopicsForEvent() {
    this.topicService.getTopicsByEventId(this.eventId).subscribe(data => {
      this.topics = data;
    });
  }

  onSubmit() {
    if (this.createTopicForm.valid) {
      const newTopic = {
        ...this.createTopicForm!.value,
        event: this.event
      };
      this.topicService.createTopic(newTopic).subscribe(
        responseData => {
          console.log(responseData);
          // Dacă este nevoie, adaugă noul topic la lista topicurilor afișate
          // this.topics.push(responseData);
          // Și/sau resetează formularul pentru o nouă intrare
          this.createTopicForm.reset();
          this.getTopicsForEvent();
        }
      );
    }
  }
}
