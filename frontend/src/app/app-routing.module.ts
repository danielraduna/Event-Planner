import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {UserComponent} from "./components/user/user.component";
import {LoginComponent} from "./components/login/login.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";
import {MyEventsComponent} from "./components/my-events/my-events.component";
import {EventDetailComponent} from "./components/event-detail/event-detail.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  { path: 'login',
    component: LoginComponent
  },
  {
    path: 'users',
    component:UserComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'user-profile',
    component: UserProfileComponent
  },
  {
    path: 'my-events',
    component: MyEventsComponent
  },
  { path: 'event/:id',
    component: EventDetailComponent
  }


]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
