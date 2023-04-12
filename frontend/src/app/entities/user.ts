import {Event} from "./event";
import {FriendsGroup} from "./friends-group";

export interface User {
  id?: number;
  username?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
  password?: string;
  birthday?: Date;
  eventsAdmin?: Event[];
  events?: Event[];
  friends?: User[];
  friendsGroups?: FriendsGroup[];
}
