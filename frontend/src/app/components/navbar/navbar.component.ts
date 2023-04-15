import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";
import {User} from "../../entities/user";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{

  user?: User;
  items: MenuItem[] = [];
  ngOnInit(): void {
    this.items = [
      {
        label: 'Contul meu',
        icon: 'pi pi-user',
        routerLink: '/user-profile'
      },
      {
        label: 'Evenimentele mele',
        icon: 'pi pi-book',
        routerLink: '/my-events'
      },
      {
        label: 'Grupurile mele',
        icon: 'pi pi-users'
      },
      {
        label: 'Deconecteaza-ma',
        icon: 'pi pi-sign-out',
        command: () => {
          this.user = undefined;
          localStorage.removeItem('user');
        },
        routerLink: '/login'
      }];
    this.user = JSON.parse(localStorage.getItem("user")!);
  }
}

