import {User} from "./user";

export interface Event {
  id: number;
  name: string;
  location: string;
  eventDate: Date;
  createDate: Date;
  admin: User;
  users: User[];
}
