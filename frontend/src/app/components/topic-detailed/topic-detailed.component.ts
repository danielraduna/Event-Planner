import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TopicService} from "../../services/topic.service";
import {Topic} from "../../entities/Topic";
import {ArgumentService} from "../../services/argument.service";
import {Argument, ArgumentType} from "../../entities/Argument";
import {UserService} from "../../services/user.service";
import {User} from "../../entities/user";

@Component({
  selector: 'app-topic-detailed',
  templateUrl: './topic-detailed.component.html',
  styleUrls: ['./topic-detailed.component.scss']
})
export class TopicDetailedComponent implements OnInit{

  topic!: Topic;
  arguments: Argument[] = [];
  newArgumentContent: string = '';
  newArgumentType: ArgumentType = ArgumentType.PRO;
  user!: User;
  constructor(private route: ActivatedRoute,
              private topicService: TopicService,
              private argumentService: ArgumentService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const topicId = +params.get('topicId')!;
      this.topicService.getTopicById(topicId).subscribe(data => {
        this.topic = data;
        this.argumentService.getArgumentsByTopicId(topicId).subscribe(response => {
          this.arguments = response;
        })
      });
    });

    this.userService.getUserById(JSON.parse(localStorage.getItem("user")!).id).subscribe(data => {
      this.user = data.body!;
    })
  }

  addArgument() {
    const newArgument: Argument = {
      topic: this.topic,
      content: this.newArgumentContent,
      type: this.newArgumentType
    };

    this.argumentService.createArgument(newArgument).subscribe(data => {
      this.argumentService.assignArgumentToTopic(data.id!, this.topic.id!).subscribe(() => {
        this.topic.arguments.push(newArgument);
        this.newArgumentContent = ''; // Reset the textarea
      });
    });

  }
}
