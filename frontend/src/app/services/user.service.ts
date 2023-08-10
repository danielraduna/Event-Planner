import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {User} from "../entities/user";
import {environment} from "../../environments/environment";
import {map, Observable} from "rxjs";
import {LoginDTO} from "../entities/loginDTO";
import {EventRequest} from "../entities/EventRequest";

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

  public createUser(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.usersUrl + "new", user, { observe: 'response' })
      .pipe(map((res: HttpResponse<User>) => res));
  }

  public login(loginDTO: LoginDTO, ) :Observable<HttpResponse<User>> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(loginDTO.username + ':' + loginDTO.password) });
    headers.set('Content-Type', 'application/json');
    return this.http.post<User>(this.usersUrl + 'login', loginDTO,  {headers: headers, observe: 'response'})
      .pipe(map((res: HttpResponse<User>) => res));
  }

  updateUser(userId: number, updatedUser: User): Observable<User> {
    return this.http.put<User>(`/api/users/update/${userId}`, updatedUser);
  }

  public sendEventRequest(senderId: number, receiverId: number, eventId: number): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.usersUrl + `sendEventRequest?senderId=${senderId}&receiverId=${receiverId}&eventId=${eventId}`, null, { observe: 'response' })
      .pipe(map((res: HttpResponse<User>) => res));
  }

}
