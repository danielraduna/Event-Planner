// src/app/entities/argument.ts

import {Topic} from "./Topic";
import {User} from "./user";

export enum ArgumentType {
  PRO = 'PRO',
  CONTRA = 'CONTRA'
}

export interface Argument {
  id?: number;
  topic: Topic;  // ID-ul topicului asociat acestui argument
  content: string;
  type: ArgumentType;
}
