import {Event} from "./event";
import {User} from "./user";

export enum PollType {
  SINGLE_CHOICE = 'SINGLE_CHOICE',
  MULTIPLE_CHOICE = 'MULTIPLE_CHOICE'
}

export interface Poll {
  id?: number;
  event?: Event;
  question: string;
  pollType: PollType;
  options: string[];
  votes: number[];
  voters: User[];
}
