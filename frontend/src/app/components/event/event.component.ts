import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../entities/event";
import {ConfirmationService, ConfirmEventType} from "primeng/api";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss'],
  providers: [ConfirmationService]
})
export class EventComponent implements OnInit {
  @Input() event!: Event;
  constructor(private confirmationService: ConfirmationService) { }

  ngOnInit(): void {


  }
  confirm1() {
    this.confirmationService.confirm({
      message: '\n' +
        'Are you sure you want to participate in this event??',
      header: 'Accept',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
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

  decline() {
    this.confirmationService.confirm({
      message: 'Are you sure you don\'t want to participate in this event?',
      header: 'Decline',
      icon: 'pi pi-info-circle',
      accept: () => {
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
