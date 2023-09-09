import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ImageEvent} from "../entities/image-event";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImageEventService {

  private imageUrl = environment.apiUrl + 'eventImages/';

  constructor(private http: HttpClient) { }

  createEventImage(eventImage: ImageEvent): Observable<ImageEvent> {
    const url = `${this.imageUrl}new`;
    return this.http.post(url, eventImage);
  }

  getEventImagesByEvent(eventId: number): Observable<ImageEvent[]> {
    const url = `${this.imageUrl}byEvent?idEvent=${eventId}`;
    return this.http.get<ImageEvent[]>(url);
  }

  assignImageToEvent(imageId: number, eventId: number): Observable<void> {
    const url = `${this.imageUrl}assignImageToEvent?idImage=${imageId}&idEvent=${eventId}`;
    return this.http.put<void>(url, {});
  }
}
