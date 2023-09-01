import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event} from "../../entities/event";
import {ConfirmationService, ConfirmEventType} from "primeng/api";
import {EventRequest} from "../../entities/EventRequest";
import {UserService} from "../../services/user.service";
import {EventRequestService} from "../../services/event-request.service";
import {User} from "../../entities/user";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss'],
  providers: [ConfirmationService]
})
export class EventComponent implements OnInit {
  @Input() event!: Event;
  @Input() request?: EventRequest;
  @Output() requestUpdated = new EventEmitter();
  @Output() withdraw = new EventEmitter<number>();


  user!: User;
  constructor(private confirmationService: ConfirmationService,
              private userService: UserService,
              private eventRequestService: EventRequestService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
  }

  confirm(): void {
    this.confirmationService.confirm({
      message: '\n' +
        'Are you sure you want to participate in this event??',
      header: 'Accept',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        if (this.request) {
          this.eventRequestService.acceptEventRequest(this.request.id).subscribe(
            () => {
              this.requestUpdated.emit();
            }
          );
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
          this.eventRequestService.rejectEventRequest(this.request.id).subscribe(
            () => {
              this.requestUpdated.emit();
            }
        );
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

  withdrawFromEvent() {
    this.userService.withdrawFromEvent(this.event.id!, this.user.id!).subscribe(() => {
      this.withdraw.emit();
    });
  }

  getTruncatedTitle(title?: string, maxLength: number = 25): string {
    if (!title) return '';
    if (title.length <= maxLength) return title;
    return title.substr(0, maxLength) + '...';
  }


}
