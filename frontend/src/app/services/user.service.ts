import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {User} from "../entities/user";
import {environment} from "../../environments/environment";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  usersUrl = environment.apiUrl + "users/";
  constructor(private http: HttpClient) { }

  public getAllUsers(): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.usersUrl + "list", {observe:'response'})
      .pipe(map((res:HttpResponse<User[]>) => res));
  }

  public getUserById(id: number): Observable<HttpResponse<User>> {
    return this.http.get<User>(this.usersUrl + "byId/" + id, {observe:'response'})
      .pipe(map((res:HttpResponse<User>) => res));
  }

  public getUserByUsername(name: string):Observable<HttpResponse<User>> {
    return this.http.get<User>(this.usersUrl + "byUsername/" + name, {observe:'response'})
      .pipe(map((res:HttpResponse<User>) => res));
  }

  public getUserByEmail(email: string):Observable<HttpResponse<User>> {
    return this.http.get<User>(this.usersUrl + "byEmail/" + email, {observe:'response'})
      .pipe(map((res:HttpResponse<User>) => res));
  }

  public assignUserToEvent(idUser: number, idEvent: number): any {
    return this.http.put(this.usersUrl + `assignToEvent?idUser=${idUser}&idEvent=${idEvent}`, {observe:'response'});
  }

  public assignUserToUser(idUser1: number, idUser2: number): any {
    return this.http.put(this.usersUrl + `assignToUser?idUser1=${idUser1}&idUser2=${idUser2}`, {observe:'response'});
  }

  public assignUserToGroup(idUser: number, idGroup: number): any {
    return this.http.put(this.usersUrl + `assignToEvent?idUser=${idUser}&idGroup=${idGroup}`, {observe:'response'});
  }

  public deleteUserToGroup(idUser: number, idGroup: number): any {
    return this.http.put(this.usersUrl + `deleteFromGroup?idUser=${idUser}&idGroup=${idGroup}`, {observe:'response'});
  }

  public makeUserAdminOfEvent(idUser: number, idEvent: number): any {
    return this.http.put(this.usersUrl + `makeAdminOfEvent?idUser=${idUser}&idEvent=${idEvent}`, {observe:'response'});
  }

  public createUser(user: User) :Observable<HttpResponse<User>> {
    return this.http.post(this.usersUrl + "new", user, {observe: 'response'})
      .pipe(map((res:HttpResponse<User>) => res));
  }
}