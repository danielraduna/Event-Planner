import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {User} from "../entities/user";
import {environment} from "../../environments/environment";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getAllUsers(): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(environment.apiUrl + "users/list", {observe:'response'})
      .pipe(map((res:HttpResponse<User[]>) => res));
  }
}
