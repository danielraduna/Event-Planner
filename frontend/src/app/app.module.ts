import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EventComponent } from './components/event/event.component';
import { UserComponent } from './components/user/user.component';
import { GroupComponent } from './components/group/group.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import {ReactiveFormsModule} from "@angular/forms";
import {ToastrModule} from "ngx-toastr";
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AvatarModule } from 'primeng/avatar';
import { MenuModule } from 'primeng/menu';
import { UserProfileComponent } from './user-profile/user-profile.component';
@NgModule({
  declarations: [
    AppComponent,
    EventComponent,
    UserComponent,
    GroupComponent,
    LoginComponent,
    DashboardComponent,
    NavbarComponent,
    UserProfileComponent
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        BrowserAnimationsModule,
        ButtonModule,
        PasswordModule,
        InputTextModule,
        CalendarModule,
        AppRoutingModule,
        ReactiveFormsModule,
        ToastrModule.forRoot({
          positionClass: 'toast-top-right',
          preventDuplicates: true,
        }),
        AvatarModule,
      MenuModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
