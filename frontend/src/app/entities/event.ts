import { User } from "./user";

export interface Event {
  id?: number;
  name: string;
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
