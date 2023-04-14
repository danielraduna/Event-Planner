import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Event} from "../entities/event";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventsUrl = environment.apiUrl + 'events/';

  constructor(private http: HttpClient) { }

  public createEvent(event: Event): Observable<HttpResponse<any>> {
    const user = JSON.parse(localStorage.getItem('user')!);
    const username = user.username;
    const password = user.password;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type', 'application/json');
    return this.http.post(this.eventsUrl + 'new', event, { headers: headers, observe: 'response' });
  }

  public getAllEvents(): Observable<HttpResponse<Event[]>> {
    const user = JSON.parse(localStorage.getItem('user')!);
    const username = user.username;
    const password = user.password;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type', 'application/json');
    return this.http.get<Event[]>(environment.apiUrl + "events/list", { headers: headers, observe:'response'})
      .pipe(map((res: HttpResponse<Event[]>) => res));
  }

  public getEventById(id: number): Observable<HttpResponse<Event>> {
    const user = JSON.parse(localStorage.getItem('user')!);
    const username = user.username;
    const password = user.password;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.http.get<Event>(this.eventsUrl + 'byId/' + id, { headers: headers, observe: 'response' })
      .pipe(map((res: HttpResponse<Event>) => res));
  }

  public getEventsByName(name: string): Observable<HttpResponse<Event[]>> {
    const user = JSON.parse(localStorage.getItem('user')!);
    const username = user.username;
    const password = user.password;
    const basicAuth = `Basic ` + Buffer.from(`${username}:${password}`).toString('base64');
    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'Authorization': basicAuth
    });
    return this.http.get<Event[]>(this.eventsUrl + 'byName/' + name, { headers: headers, observe: 'response' })
      .pipe(map((res: HttpResponse<Event[]>) => res));
  }

  public getEventsByUser(idUser: number): Observable<HttpResponse<Event[]>> {
    return this.http.get<Event[]>(this.eventsUrl + 'byUser/' + idUser, { observe: 'response' })
      .pipe(map((res: HttpResponse<Event[]>) => res));
  }

  public updateEvent(event: Event): Observable<HttpResponse<any>> {
    const user = JSON.parse(localStorage.getItem('user')!);
    const username = user.username;
    const password = user.password;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type', 'application/json');
    return this.http.put(this.eventsUrl + 'update', event, { headers: headers, observe: 'response' });
  }

  public deleteEvent(event: Event): Observable<HttpResponse<any>> {
    const user = JSON.parse(localStorage.getItem('user')!);
    const username = user.username;
    const password = user.password;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.http.delete(this.eventsUrl + 'delete', { headers: headers, body: event, observe: 'response' });
  }

}
