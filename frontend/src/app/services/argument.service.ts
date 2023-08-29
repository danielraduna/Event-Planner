import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Argument} from "../entities/Argument";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ArgumentService {

  argumentUrl = environment.apiUrl + "arguments/";

  constructor(private http: HttpClient) { }

  createArgument(argument: Argument): Observable<Argument> {
    return this.http.post<Argument>(this.argumentUrl + "new", argument);
  }

  getArgumentsByTopicId(topicId: number): Observable<Argument[]> {
    return this.http.get<Argument[]>(`${this.argumentUrl}topic/${topicId}`);
  }

  getArgumentById(id: number): Observable<Argument> {
    return this.http.get<Argument>(`${this.argumentUrl}${id}`);
  }

  assignArgumentToTopic(argumentId: number, topicId: number): Observable<void> {
    const url = `${this.argumentUrl}${argumentId}/assign-to-topic/${topicId}`;
    return this.http.post<void>(url, {});
  }
  updateArgument(argument: Argument): Observable<Argument> {
    return this.http.put<Argument>(`${this.argumentUrl}${argument.id}`, argument);
  }

  deleteArgument(id: number): Observable<void> {
    return this.http.delete<void>(`${this.argumentUrl}${id}`);
  }
}
