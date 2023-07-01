import { User } from "./user";
import { Event } from "./event";

export interface EventRequest {
  id: number;
  sender: User;
  receiver: User;
  event: Event;
  accepted: boolean;
}

