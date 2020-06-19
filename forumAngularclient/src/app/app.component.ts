import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './_services/token-storage.service';
import {AuthService} from './_services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  adminLogged = false;
  moderatorLogged = false;
  username: string;

  constructor(private tokenStorageService: TokenStorageService,
              public authService: AuthService) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.adminLogged = this.roles.includes('ROLE_ADMIN');
      this.moderatorLogged = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
