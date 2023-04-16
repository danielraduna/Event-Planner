import {User} from "./user";

export interface ProfilePicture {
  id?: number;
  imageData: string;
  user?: User;
}
