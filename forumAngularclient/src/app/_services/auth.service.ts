import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from './token-storage.service';

const AUTH_API = 'http://localhost:8081/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  public login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  public register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }

  public isAdminLoggedIn() {
    let roles: string[];
    roles = this.tokenStorage.getUser().roles;
    return roles.includes('ROLE_ADMIN');
  }

  public isModeratorLoggedIn() {
    let roles: string[];
    roles = this.tokenStorage.getUser().roles;
    return roles.includes('ROLE_MODERATOR') || this.isAdminLoggedIn();
  }

  public isVipLoggedIn() {
    let roles: string[];
    roles = this.tokenStorage.getUser().roles;
    return roles.includes('ROLE_VIP') || this.isModeratorLoggedIn();
  }

  public isUserLoggedIn() {
    let roles: string[];
    roles = this.tokenStorage.getUser().roles;
    return roles.includes('ROLE_USER') || this.isVipLoggedIn();
  }
}
