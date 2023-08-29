import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {UserComponent} from "./components/user/user.component";
import {LoginComponent} from "./components/login/login.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";
import {MyEventsComponent} from "./components/my-events/my-events.component";
import {EventDetailComponent} from "./components/event-detail/event-detail.component";
import {ParticipantsListComponent} from "./components/participants-list/participants-list.component";
import {CreateEventComponent} from "./components/create-event/create-event.component";
import {FriendRequestsComponent} from "./components/friend-requests/friend-requests.component";
import {EventPollsComponent} from "./components/event-polls/event-polls.component";
import {EventTopicsComponent} from "./components/event-topics/event-topics.component";
import {TopicComponent} from "./components/topic/topic.component";
import {ArgumentComponent} from "./components/argument/argument.component";
import {TopicDetailedComponent} from "./components/topic-detailed/topic-detailed.component";

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
  },
  {
    path: 'event/:id/participants',
    component: ParticipantsListComponent,
  },
  {
    path: 'create-event',
    component: CreateEventComponent
  },
  { path: 'friend-requests',
    component: FriendRequestsComponent
  },
  {
    path: 'event/:eventId/polls',
    component: EventPollsComponent
  },
  {
    path: 'event/:eventId/topics',
    component: EventTopicsComponent
  },
  {
    path: 'topics/:topicId',
    component: TopicComponent
  },
  {
    path: 'topics/:topicId/arguments/:argumentId',
    component: ArgumentComponent
  },
  {
    path: 'topic/:topicId/detailed',
    component: TopicDetailedComponent
  }

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
