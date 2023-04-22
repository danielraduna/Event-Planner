import { User } from "./user";
import { Event } from "./event";

export interface EventRequest {
  id?: number;
  user?: User;
  event?: Event;
  accepted?: boolean;
}
