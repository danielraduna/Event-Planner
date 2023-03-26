import { Component, OnInit } from '@angular/core';
import {GroupService} from "../../services/group.service";
import {FriendsGroup} from "../../entities/friends-group";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements OnInit {

  group?: FriendsGroup;
  constructor(private groupService: GroupService) { }

  ngOnInit(): void {
    this.groupService.getGroupById(1).subscribe(data => {
      if(data.body) {
        this.group = data.body;
        console.log(this.group);
      }
    });
  }

}
