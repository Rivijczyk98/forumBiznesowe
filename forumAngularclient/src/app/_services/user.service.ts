import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {User} from '../_model/user';

const API = 'http://localhost:8081/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(API + '/all');
  }

  public deleteUser(id: number) {
    return this.http.delete<User>(API + '/delete?id=' + id);
  }

  /*
  public updateUser(user: User) {
    return this.http.put<User>(API + '/update', user);
  }
*/

  public giveRole(user: User, role: string) {
    return this.http.patch<User>(API + '/give?role=' + role, user);
  }

  public getUsername(id: number): Observable<any> {
    return this.http.get<any>(API + '/username?id=' + id);
  }
}
