import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {User} from '../_model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private getUsersUrl: string;
  private deleteUserUrl: string;
  private giveRoleUrl: string;

  constructor(private http: HttpClient) {
    this.getUsersUrl = 'http://localhost:8081/users/all';
    this.deleteUserUrl = 'http://localhost:8081/users/delete';
    this.giveRoleUrl = 'http://localhost:8081/users/giveRole';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.getUsersUrl);
  }

  public deleteUser(id: number) {
    return this.http.delete<User>(this.deleteUserUrl + '?id=' + id);
  }

  /*
  public updateUser(user: User) {
    return this.http.put<User>(this.usersUrl, user);
  }
*/

  public giveRole(user: User, role: string) {
    return this.http.patch<User>(this.giveRoleUrl + '?role=' + role, user);
  }


}
