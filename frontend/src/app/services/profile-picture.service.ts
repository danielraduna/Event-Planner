import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ProfilePicture} from "../entities/profile-picture";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProfilePictureService {
  private apiUrl = 'http://localhost:8080/api/profile-pictures';
  constructor(private http: HttpClient) { }

  getAllProfilePictures(): Observable<ProfilePicture[]> {
    return this.http.get<ProfilePicture[]>(this.apiUrl);
  }

  getProfilePictureById(id: number): Observable<ProfilePicture> {
    return this.http.get<ProfilePicture>(`${this.apiUrl}/${id}`);
  }

  createProfilePicture(profilePicture: ProfilePicture, userId: number): Observable<ProfilePicture> {
    const formData = new FormData();
    formData.append('userId', userId.toString());
    formData.append('imageData', profilePicture.imageData);

    return this.http.post<ProfilePicture>(`${this.apiUrl}/new`, formData);
  }

  updateProfilePicture(id: number, profilePicture: ProfilePicture): Observable<ProfilePicture> {
    return this.http.put<ProfilePicture>(`${this.apiUrl}/${id}`, profilePicture);
  }

  deleteProfilePicture(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  setProfilePictureToUser(userId: number, profilePictureId: number): Observable<any> {
    const params = new HttpParams().set('userId', userId.toString()).set('profilePictureId', profilePictureId.toString());
    return this.http.post<any>(`${this.apiUrl}/assignProfilePictureToUser`, { params });
  }

}
