import { User } from "./user";

export enum EventType {
  PUBLIC = 'PUBLIC',
  PRIVATE = 'PRIVATE'
}

export interface Event {
  id?: number;
  name: string;
  type?: EventType;
  location: string;
  description: string;
  startDate: Date;
  stopDate: Date;
  createDate: Date;
  startTime: string;
  endTime: string;
  admin: User;
  users: User[];
  extraDetails?: string[];
}
