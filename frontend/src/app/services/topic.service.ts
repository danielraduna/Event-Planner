import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Topic} from "../entities/Topic";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  topicUrl = environment.apiUrl + "topics/";

  constructor(private http: HttpClient) { }

  createTopic(topic: Topic): Observable<Topic> {
    return this.http.post<Topic>(this.topicUrl + "new", topic);
  }

  getTopicsByEventId(eventId: number): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.topicUrl}event/${eventId}`);
  }

  getTopicById(id: number): Observable<Topic> {
    return this.http.get<Topic>(`${this.topicUrl}${id}`);
  }

  updateTopic(topic: Topic): Observable<Topic> {
    return this.http.put<Topic>(`${this.topicUrl}${topic.id}`, topic);
  }

  deleteTopic(id: number): Observable<void> {
    return this.http.delete<void>(`${this.topicUrl}${id}`);
  }
}
