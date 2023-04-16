import {Component, OnInit} from '@angular/core';
import {User} from "../../entities/user";
import {UserService} from "../../services/user.service";
import {ProfilePictureService} from "../../services/profile-picture.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit{
  value = 2;
  user?: User;
  constructor(private userService: UserService,
              private pictureService: ProfilePictureService) { }
  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem("user")!);
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

}
