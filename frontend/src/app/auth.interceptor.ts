import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from "./entities/user";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const currentUser: User = JSON.parse(localStorage.getItem('user')!);
    if (currentUser  && !request.url.includes('users/new')) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${btoa(currentUser.username + ':' + currentUser.password)}`,
        },
      });
    }
    return next.handle(request);
  }
}
