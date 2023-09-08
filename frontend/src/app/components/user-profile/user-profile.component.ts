import {Component, OnInit} from '@angular/core';
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";
import {ProfilePictureService} from "../../services/profile-picture.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FriendRequestService} from "../../services/friend-request.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit{
  value = 2;
  user?: User;
  isModalOpen = false;
  currentUser!: User;
  addFriendButton = true;
  isRequestSent = false;
  isAlreadyFriend = false;
  constructor(private userService: UserService,
              private pictureService: ProfilePictureService,
              private route: ActivatedRoute,
              private router: Router,
              private friendRequestService: FriendRequestService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const userId = params['userId'];
      if (userId) {
        this.userService.getUserById(userId).subscribe(user => {
          this.user = user.body!;
          this.currentUser = JSON.parse(localStorage.getItem("user")!);

          if(this.currentUser.id === this.user!.id)  {
            this.addFriendButton = false;
          }
          this.userService.getUserFriends(this.currentUser.id!).subscribe(data => {
            let i;
            for(i = 0; i <= data.body!.length; i++) {
              if(data.body![i].id === this.user?.id) {
                this.isAlreadyFriend = true;
                break;
              }
            }
            if(this.currentUser.id === this.user!.id || this.isAlreadyFriend)  {
              this.addFriendButton = false;
            }
            if(this.addFriendButton) {
              this.friendRequestService.checkFriendRequestExists(this.currentUser.id!, this.user!.id!).subscribe(resp => {
                this.isRequestSent = resp;
              });
            }
          })



        });
      }
    });
  }


  openModal() {
    this.isModalOpen = true;
  }

  onEdit() {
    this.isModalOpen = true;
  }

  onModalClose() {
    this.isModalOpen = false;
  }

  onUpdate() {
    if(this.user) {
      this.userService.updateUser(this.user.id!, this.user).subscribe(updatedUser => {
        this.user = updatedUser; // Actualizăm datele de utilizator local
        this.isModalOpen = false;
      });
    }
  }


  onFileSelected(event: Event) {
    const file = (event.target as HTMLInputElement).files![0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        const imageData = e.target!.result as string;
        const image = {
          imageData: imageData
        }
        this.pictureService.createProfilePicture(image, this.user?.id!).subscribe(
          (data) => {
            this.user!.profilePicture = image;
            localStorage.setItem("user", JSON.stringify(this.user));
          });
      };
      reader.readAsDataURL(file);
    }
  }

  sendFriendRequest(): void {
    this.userService.sendFriendRequest(this.currentUser.id!, this.user?.id!).subscribe(data => {
      this.isRequestSent = true;
      this.addFriendButton = false;
    });
  }

  unfriend(): void {
    this.userService.unfriend(this.currentUser.id!, this.user?.id!).subscribe(data => {
      this.addFriendButton = true;
      this.isAlreadyFriend = false;
    });
  }

}
