import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from "./entities/user";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const currentUser: User = JSON.parse(localStorage.getItem('user')!);
    console.log('Current User:', currentUser);
    if (currentUser) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${btoa(currentUser.username + ':' + currentUser.password)}`,
        },
      });
    }
    console.log('Modified Request:', request);
    return next.handle(request);
  }
}
