import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EventComponent } from './components/event/event.component';
import { UserComponent } from './components/user/user.component';
import { GroupComponent } from './components/group/group.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import {AppRoutingModule} from "./app-routing.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastrModule} from "ngx-toastr";
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AvatarModule } from 'primeng/avatar';
import { MenuModule } from 'primeng/menu';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { RatingModule } from 'primeng/rating';
import { MyEventsComponent } from './components/my-events/my-events.component';
import {AuthInterceptor} from "./auth.interceptor";
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { FileUploadModule } from 'primeng/fileupload';
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import { ParticipantsListComponent } from './components/participants-list/participants-list.component';
import { MyModalComponent } from './components/my-modal/my-modal.component';
import { StepsModule } from 'primeng/steps';
import { CreateEventComponent } from './components/create-event/create-event.component';
import { ToastModule } from "primeng/toast";
import { RadioButtonModule } from 'primeng/radiobutton';
import { TooltipModule } from 'primeng/tooltip';
import { FriendRequestsComponent } from './components/friend-requests/friend-requests.component';
import { RequestComponent } from './components/request/request.component';
import { CheckboxModule } from 'primeng/checkbox';
import { PollComponent } from './components/poll/poll.component';
import { EventPollsComponent } from './components/event-polls/event-polls.component';
import { CardModule } from 'primeng/card';
import { ListboxModule } from 'primeng/listbox';
import {DropdownModule} from "primeng/dropdown";
@NgModule({
  declarations: [
    AppComponent,
    EventComponent,
    UserComponent,
    GroupComponent,
    LoginComponent,
    DashboardComponent,
    NavbarComponent,
    UserProfileComponent,
    MyEventsComponent,
    ForgotPasswordComponent,
    EventDetailComponent,
    ParticipantsListComponent,
    MyModalComponent,
    CreateEventComponent,
    FriendRequestsComponent,
    RequestComponent,
    PollComponent,
    EventPollsComponent
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
    MenuModule,
    RatingModule,
    FormsModule,
    ConfirmDialogModule,
    FileUploadModule,
    StepsModule,
    ToastModule,
    RadioButtonModule,
    TooltipModule,
    CheckboxModule,
    CardModule,
    ListboxModule,
    DropdownModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },],
  bootstrap: [AppComponent]
})
export class AppModule { }
