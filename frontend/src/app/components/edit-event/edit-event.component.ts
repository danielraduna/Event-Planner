import {Component, OnInit} from '@angular/core';
import {Event} from "../../entities/event";
import {ActivatedRoute, Router} from "@angular/router";
import {PollService} from "../../services/poll.service";
import {FormBuilder} from "@angular/forms";
import {EventService} from "../../services/event.service";
import {MenuItem} from "primeng/api";
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";

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

  selectedFriends: User[] = [];
  filteredFriends: User[] = [];
  searchText: string = '';
  friends: User[] = [];

  currentUser!: User;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private pollService: PollService,
              private fb: FormBuilder,
              private userService: UserService,
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

        this.currentUser = JSON.parse(localStorage.getItem("user")!);
        this.userService.getUserById(this.currentUser.id!).subscribe(data => {
          this.currentUser = data.body!;
          this.userService.getUserFriends(this.currentUser.id!).subscribe(data => {
            this.friends = data.body!;
            this.filteredFriends = this.friends;
            let i, j;
            for(i = 0; i < this.friends.length; i++)
              for(j = 0; j < this.event.users.length; j++) {
                if(this.friends[i].id === this.event.users[j].id) {
                  this.selectedFriends.push(this.friends[i]);
                  break;
                }
              }
            console.log(this.selectedFriends);
          });
        });
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
      for(const selectFriend of this.selectedFriends) {
        let ok = true;
        for(const user of this.event.users) {
          if(selectFriend.id === user.id) {
            ok = false;
          }
        }
        if(ok)
          this.userService.sendEventRequest(this.currentUser.id!, selectFriend.id!, this.event.id!).subscribe();
      }
      this.router.navigate(['/event', this.event.id]);
    });
  }

  filterFriends() {
    if (this.searchText === '' || this.searchText === null) {
      this.filteredFriends = this.friends;
    } else {
      this.filteredFriends = this.friends.filter((friend) =>
        friend.username!.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }
  }
}
