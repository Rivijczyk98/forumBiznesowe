import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {User} from '../_model/user';

const API = "http://localhost:8081/users"

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private getUsersUrl: string;
  private deleteUserUrl: string;
  private giveRoleUrl: string;

  constructor(private http: HttpClient) {
    this.getUsersUrl = API + '/all';
    this.deleteUserUrl = 'http://localhost:8081/users/delete';
    this.giveRoleUrl = 'http://localhost:8081/users/giveRole';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.getUsersUrl);
  }

  public findUser(id: number): Observable<User> {
    return this.http.get<User>(API + '/find?id=' + id)
  }

  public deleteUser(id: number) {
    return this.http.delete<User>(this.deleteUserUrl + '?id=' + id);
  }

  /*
  public updateUser(user: User) {
    return this.http.put<User>(this.usersUrl, user);
  }
*/

  // TODO przenieść trzy metody zmiany roli do jednej
  public giveRole(user: User, role: string) {
    console.log(role);
    console.log(user);
    return this.http.patch<User>(this.giveRoleUrl + '?role=' + role, user);
  }

  public getUsername(id: number): Observable<any>{
    return this.http.get<any>(API + '/username?id=' + id);
  }


}
