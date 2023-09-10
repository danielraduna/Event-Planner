import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {UserService} from "../../services/user.service";
import {User} from "../../entities/user";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit{
  user?: User;
  friends: User[] = [];
  filteredFriends: User[] = [];
  searchText: string = '';
  users: User[] = [];
  constructor(private route: ActivatedRoute,
              private userService: UserService
  ){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = params['userId'];
      if (userId) {
        this.userService.getUserById(userId).subscribe(user => {
          this.user = user.body!;
          this.userService.getUserFriends(this.user.id!).subscribe(friends => {
            this.friends = friends.body!;
            this.filteredFriends = this.friends;
          })
        });
      }
    });
    this.userService.getAllUsers().subscribe(data => {
      this.users = data.body!;
    })
  }

  filterFriends() {
    if (this.searchText === '' || this.searchText === null) {
      this.filteredFriends = this.friends;
    } else {
      this.filteredFriends = this.friends.filter((friend) =>
        friend.username!.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }

    if (this.filteredFriends.length === 0) {
      this.filteredFriends = this.users.filter((user) =>
        user.username!.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }
  }

  unfriend(idFriend: number): void {
    this.userService.unfriend(this.user?.id!, idFriend).subscribe();
  }

  sendFriendRequest(idFriend: number): void {
    this.userService.sendFriendRequest(this.user?.id!, idFriend).subscribe();
  }
}
