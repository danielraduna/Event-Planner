import {User} from "./user";

export interface FriendsGroup {
  id: number;
  name: string;
  numberParticipants: number;
  description: string;
  users: User[];
}
