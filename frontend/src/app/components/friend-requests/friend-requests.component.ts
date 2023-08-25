import {Component, OnInit} from '@angular/core';
import {FriendRequest} from "../../entities/friend-request";
import {FriendRequestService} from "../../services/friend-request.service";
import {User} from "../../entities/user";

@Component({
  selector: 'app-friend-requests',
  templateUrl: './friend-requests.component.html',
  styleUrls: ['./friend-requests.component.scss']
})
export class FriendRequestsComponent implements OnInit{
  friendRequests: FriendRequest[] = [];
  user!: User;

  constructor(
    private friendRequestService: FriendRequestService,
  ) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
    this.friendRequestService.getReceivedFriendRequests(this.user.id!).subscribe(requests => {
      this.friendRequests = requests.body!;
    });
  }

  updateRequests(index: number): void {
    this.friendRequests.splice(index, 1);
  }
}
