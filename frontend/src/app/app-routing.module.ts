import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {UserComponent} from "./components/user/user.component";
import {EventComponent} from "./components/event/event.component";
import {GroupComponent} from "./components/group/group.component";
import {LoginComponent} from "./components/login/login.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";
import {MyEventsComponent} from "./components/my-events/my-events.component";

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
  }


]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
