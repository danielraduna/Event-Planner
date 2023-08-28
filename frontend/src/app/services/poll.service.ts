import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Poll} from "../entities/poll";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PollService {

  private baseUrl = environment.apiUrl + 'polls/';

  constructor(private http: HttpClient) { }

  createPoll(poll: Poll): Observable<Poll> {
    return this.http.post<Poll>(this.baseUrl + "new", poll);
  }

  getPoll(id: number): Observable<Poll> {
    return this.http.get<Poll>(`${this.baseUrl}${id}`);
  }

  getPollsByEventId(eventId: number): Observable<Poll[]> {
    return this.http.get<Poll[]>(`${this.baseUrl}event/${eventId}`);
  }

  updatePoll(poll: Poll): Observable<Poll> {
    return this.http.put<Poll>(environment.apiUrl + 'polls', poll);
  }

  deletePoll(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }

  voteForOption(id: number, optionIndex: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}${id}/vote/${optionIndex}`, {});
  }
}
