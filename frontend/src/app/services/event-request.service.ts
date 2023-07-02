import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {map, Observable} from "rxjs";
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

  acceptEventRequest(idRequest: number): Observable<HttpResponse<any>> {
    return this.http.post<any>(this.requestsUrl + `accept/${idRequest}`, {}, { observe: 'response' });
  }

  rejectEventRequest(idRequest: number): Observable<HttpResponse<any>> {
    return this.http.post<any>(this.requestsUrl + `reject/${idRequest}`, {}, { observe: 'response' });
  }



  getReceivedEventRequests(idUser: number): Observable<HttpResponse<EventRequest[]>> {
    return this.http.get<EventRequest[]>(this.requestsUrl + `received-invitations/${idUser}`, { observe: 'response' })
      .pipe(map((res: HttpResponse<EventRequest[]>) => res));
  }
}
