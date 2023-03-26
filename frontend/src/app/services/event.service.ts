import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {map, Observable} from "rxjs";
import * as http from "http";
import {Event} from "../entities/event";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  public getAllEvents(): Observable<HttpResponse<Event[]>> {
    return this.http.get<Event[]>(environment.apiUrl + "events/list", {observe:'response'})
      .pipe(map((res: HttpResponse<Event[]>) => res));
  }
}
