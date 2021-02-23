import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { LogoutService } from '../services/logout.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private router: Router,
    public authService: AuthService,
    private logoutService: LogoutService
  ) { }


  ngOnInit(): void {
  }

  logout() {
    this.logoutService.logout()
      .then(() => {
        window.location.href = '/';
      })
  }

}
