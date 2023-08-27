import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Event} from "../../entities/event";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";
import {EventRequestService} from "../../services/event-request.service";
import {User} from "../../entities/user";
import {Router} from "@angular/router";
import {forkJoin, switchMap} from "rxjs";

interface EventDetails {
  name?: string;          // Numele evenimentului
  description?: string;
  location?: string,  // Adaugă această linie// Descrierea evenimentului
  type?: 'singleDay' | 'multiDay';  // Tipul de eveniment: pe o singură zi sau pe mai multe zile
  date?: Date | null;
  startTime?: string | null;
  endTime?: string | null;
  dateRange?: [Date, Date] | null;
}

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit{
  items!: MenuItem[];
  activeIndex: number = 0;

  showExtraDetailsInput: boolean = false;
  extraDetails: string[] = [];
  tempExtraDetails: string = '';

  event!: Event;
  eventDetails: EventDetails = {};
  currentUser!: User;

  friends: User[] = [];
  selectedFriends: User[] = [];

  constructor(private eventService: EventService,
              private router: Router,
              private userService: UserService,
              private eventRequestService: EventRequestService) {
  }
  ngOnInit(): void {

    this.items = [
      { label: 'Informatii' },
      { label: 'Invitatii' },
      { label: 'Data' },
      { label: 'Ultimele detalii' },
    ];
    this.currentUser = JSON.parse(localStorage.getItem("user")!);
    this.userService.getUserById(this.currentUser.id!).subscribe(data => {
      this.currentUser = data.body!;
      this.userService.getUserFriends(this.currentUser.id!).subscribe(data => {
        this.friends = data.body!;
      });
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
    this.eventDetails.date = null;
    this.eventDetails.startTime = null;
    this.eventDetails.endTime = null;
    this.eventDetails.dateRange = null;
  }

  addExtraDetails() {
    if (this.tempExtraDetails.trim()) { // doar dacă există un detaliu valid
      this.extraDetails.push(this.tempExtraDetails.trim());
      this.tempExtraDetails = '';
    }
    this.showExtraDetailsInput = false;
  }

  createEvent(): void {
    this.userService.getUserById(this.currentUser.id!).subscribe(data => {
      const attachedUser: User = data.body!;

      const eventToSend: Event = {
        name: this.eventDetails.name!,
        description: this.eventDetails.description!,
        location: this.eventDetails.location!,
        startDate: (this.eventDetails.type === 'singleDay') ? this.eventDetails.date! : this.eventDetails.dateRange![0],
        stopDate: (this.eventDetails.type === 'singleDay') ? this.eventDetails.date! : this.eventDetails.dateRange![1],
        startTime: this.eventDetails.startTime!,
        endTime: this.eventDetails.endTime!,
        createDate: new Date(),
        admin: attachedUser,
        users: [attachedUser],
        extraDetails: this.extraDetails
      };

      this.eventService.createEvent(eventToSend).subscribe((response) => {

        this.userService.assignUserToEvent(this.currentUser.id!, response.body.id).subscribe(() => {
          for(const friend of this.selectedFriends) {
            this.userService.sendEventRequest(this.currentUser.id!, friend.id!, response.body.id).subscribe();
          }
          this.router.navigate(['/dashboard']);
        });
      });

    });
  }

}
