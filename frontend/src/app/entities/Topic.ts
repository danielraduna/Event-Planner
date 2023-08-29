// src/app/entities/topic.ts

import {Argument} from "./Argument";

export interface Topic {
  id?: number;
  event: Event;  // ID-ul evenimentului asociat acestui topic
  title: string;
  arguments: Argument[];
}
