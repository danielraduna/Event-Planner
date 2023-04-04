import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{

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
        icon: 'pi pi-book'
      },
      {
        label: 'Grupurile mele',
        icon: 'pi pi-users'
      },
      {
        label: 'Deconecteaza-ma',
        icon: 'pi pi-sign-out',
        routerLink: '/login'
      }];
  }
}

