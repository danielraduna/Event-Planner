import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {FriendsGroup} from "../entities/friends-group";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(private http: HttpClient) { }

  public getGroupById(id: number): Observable<HttpResponse<FriendsGroup>> {
    return this.http.get<FriendsGroup>(environment.apiUrl + "groups/byId/" + id, {observe: 'response'})
      .pipe(map((res: HttpResponse<FriendsGroup>) => res));
}
}
