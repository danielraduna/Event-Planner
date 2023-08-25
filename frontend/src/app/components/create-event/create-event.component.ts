import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Event} from "../../entities/event";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";
import {EventRequestService} from "../../services/event-request.service";
import {User} from "../../entities/user";
import {Router} from "@angular/router";

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

  constructor(private eventService: EventService,
              private router: Router) {
  }
  ngOnInit(): void {

    this.items = [
      { label: 'Informatii' },
      { label: 'Invitatii' },
      { label: 'Data' },
      { label: 'Ultimele detalii' },
    ];
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
    this.currentUser = JSON.parse(localStorage.getItem("user")!);
    const eventToSend: Event = {
      name: this.eventDetails.name!,
      description: this.eventDetails.description!, // adăugăm descrierea
      location: this.eventDetails.location!,
      startDate: (this.eventDetails.type === 'singleDay') ? this.eventDetails.date! : this.eventDetails.dateRange![0],
      stopDate: (this.eventDetails.type === 'singleDay') ? this.eventDetails.date! : this.eventDetails.dateRange![1],
      startTime: this.eventDetails.startTime!, // adăugăm ora de începere
      endTime: this.eventDetails.endTime!, // adăugăm ora de terminare
      createDate: new Date(),
      admin: this.currentUser,
      users: [this.currentUser],
      extraDetails: this.extraDetails
    };


    this.eventService.createEvent(eventToSend).subscribe(
      (response) => {
        console.log("Eveniment creat cu succes!", response);
        this.router.navigate(['/dashboard']);
      }
    );
    console.log(eventToSend);
  }
}
