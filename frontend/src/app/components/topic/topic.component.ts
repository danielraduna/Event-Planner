import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../entities/Topic";
import {TopicService} from "../../services/topic.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent implements OnInit{

  @Input() topic!: Topic;
  createTopicForm: FormGroup;


  constructor(private topicService: TopicService) {
    this.createTopicForm = new FormGroup({
      'title': new FormControl(null, Validators.required)
    });
  }

  ngOnInit(): void {
  }


  countArguments(type: 'PRO' | 'CONTRA'): number {
    return this.topic.arguments.filter(arg => arg.type === type).length;
  }



}
