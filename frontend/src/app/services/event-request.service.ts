import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EventRequest} from "../entities/EventRequest";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class EventRequestService {

  private requestsUrl = environment.apiUrl + 'eventrequests/'; // URL-ul la API-ul tău

  constructor(private http: HttpClient) { }

  getAllEventRequests(): Observable<EventRequest[]> {
    return this.http.get<EventRequest[]>(`${this.requestsUrl}/list`);
  }

  acceptEventRequest(id: number): Observable<EventRequest> {
    return this.http.put<EventRequest>(`${this.requestsUrl}/accept/${id}`, {});
  }

  rejectEventRequest(id: number): Observable<void> {
    return this.http.delete<void>(`${this.requestsUrl}/reject/${id}`);
  }}
