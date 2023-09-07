import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {FriendRequest} from "../entities/friend-request";
import {map, Observable} from "rxjs";
import {User} from "../entities/user";
import {environment} from "../../environments/environment";
import {EventRequest} from "../entities/EventRequest";

@Injectable({
  providedIn: 'root'
})
export class FriendRequestService {

  private requestsUrl = environment.apiUrl + 'friend-requests/'; // URL-ul la API-ul tău

  constructor(private http: HttpClient) { }

  getReceivedFriendRequests(userId: number): Observable<HttpResponse<FriendRequest[]>> {
    return this.http.get<FriendRequest[]>(`${this.requestsUrl}received/${userId}`, { observe: 'response' })
      .pipe(map((res: HttpResponse<FriendRequest[]>) => res));
  }

  acceptFriendRequest(requestId: number): Observable<HttpResponse<any>> {
    return this.http.post<void>(`${this.requestsUrl}accept/${requestId}`, {}, { observe: 'response' });
  }

  rejectFriendRequest(requestId: number): Observable<void> {
    return this.http.delete<void>(`${this.requestsUrl}/${requestId}`);
  }

  checkFriendRequestExists(idSender: number, idReceiver: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.requestsUrl}existFriendRequest?idSender=${idSender}&idReceiver=${idReceiver}`);
  }
}
