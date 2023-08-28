import {Component, Input, OnInit} from '@angular/core';
import {Poll} from "../../entities/poll";
import {ActivatedRoute} from "@angular/router";
import {PollService} from "../../services/poll.service";
import {FormBuilder} from "@angular/forms";
import {EventService} from "../../services/event.service";
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.scss']
})
export class PollComponent implements OnInit{
  @Input() pollData!: Poll;
  selectedOption: string | string[] = "";
  isMultipleChoice: boolean = true; // Setează true pentru multiple choice
  hasVoted: boolean = false;
  user: User;
  constructor(private pollService: PollService,
              private userService: UserService) {
    this.user = JSON.parse(localStorage.getItem("user")!);
    this.userService.getUserById(this.user.id!).subscribe(data => {
      this.user = data.body!;
      this.hasVoted = this.pollData.voters.some(voter => voter.id === this.user.id);  // presupun că fiecare user are un ID
      console.log(this.pollData)
    })

  }

  ngOnInit(): void {
    this.isMultipleChoice = this.pollData.pollType === 'MULTIPLE_CHOICE';

  }

  submitVote() {
    if(this.selectedOption) {
      this.pollData.question += "1";
      if (this.isMultipleChoice) {
        const selectedOptions = this.selectedOption as string[];

        selectedOptions.forEach(selected => {
          const index = this.pollData.options.findIndex(option => option === selected);
          if (index !== -1) {
            this.pollData.votes[index]++;  // Cresc numărul de voturi pentru opțiunea respectivă
          }
        });
      }
      else {
        const selected = this.selectedOption as string;
        const index = this.pollData.options.findIndex(option => option === selected);
        if (index !== -1) {
          this.pollData.votes[index]++;  // Cresc numărul de voturi pentru opțiunea respectivă
        }
      }
      this.hasVoted = true;
      this.userService.assignUserToPoll(this.user.id!, this.pollData.id!).subscribe();
      this.pollService.updatePoll(this.pollData).subscribe();
    }

  }
}
