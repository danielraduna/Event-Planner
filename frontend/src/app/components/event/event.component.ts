import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../entities/event";
import {ConfirmationService, ConfirmEventType} from "primeng/api";
import {EventRequest} from "../../entities/EventRequest";
import {UserService} from "../../services/user.service";
import {EventRequestService} from "../../services/event-request.service";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss'],
  providers: [ConfirmationService]
})
export class EventComponent implements OnInit {
  @Input() event!: Event;
  @Input() request?: EventRequest;
  constructor(private confirmationService: ConfirmationService,
              private userService: UserService,
              private eventrequestService: EventRequestService) { }

  ngOnInit(): void {


  }
  confirm(): void {
    this.confirmationService.confirm({
      message: '\n' +
        'Are you sure you want to participate in this event??',
      header: 'Accept',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        if (this.request) {
          this.eventrequestService.acceptEventRequest(this.request.id).subscribe();
        }
      },
      reject: (type: any) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            break;
          case ConfirmEventType.CANCEL:
            break;
        }
      }
    });
  }

  reject(): void {
    this.confirmationService.confirm({
      message: 'Are you sure you don\'t want to participate in this event?',
      header: 'Decline',
      icon: 'pi pi-info-circle',
      accept: () => {
        if (this.request) {
          this.eventrequestService.rejectEventRequest(this.request.id).subscribe();
        }
      },
      reject: (type: any) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            break;
          case ConfirmEventType.CANCEL:
            break;
        }
      }
    });
  }

}
