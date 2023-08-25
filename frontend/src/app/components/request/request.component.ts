import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FriendRequest} from "../../entities/friend-request";
import {User} from "../../entities/user";
import {ConfirmationService, ConfirmEventType} from "primeng/api";
import {FriendRequestService} from "../../services/friend-request.service";

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss'],
  providers: [ConfirmationService]

})
export class RequestComponent  implements OnInit {
  @Input() request?: FriendRequest;
  @Output() requestUpdated = new EventEmitter<void>();
  user!: User;

  constructor(private confirmationService: ConfirmationService,
              private friendRequestService: FriendRequestService) {
  }
  ngOnInit(): void {
  }

  confirm(): void {
    this.confirmationService.confirm({
      message: '\n' +
        'Are you sure you want to accept this friend request?',
      header: 'Accept',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        if (this.request) {
          this.friendRequestService.acceptFriendRequest(this.request.id).subscribe(
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
          this.friendRequestService.rejectFriendRequest(this.request.id).subscribe(
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
}
