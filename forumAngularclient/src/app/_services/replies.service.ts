import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reply } from '../_model/reply';

const API = 'http://localhost:8081/replies';

@Injectable({
  providedIn: 'root'
})
export class RepliesService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<any[]> {
    return this.http.get<Reply[]>(API + '/all');
  }

  public findById(id: number): Observable<any> {
    return this.http.get<Reply>(API + '?id=' + id)
  }

  public findAllByPost(id: number): Observable<any[]> {
    return this.http.get<Reply[]>(API + '/post?id=' + id);
  }

  public addReply(reply: Reply) {
    return this.http.post<Reply>(API, reply);
  }

  public updateReply(reply: Reply){
    return this.http.put<Reply>(API, reply);
  }

  public deleteReply(id: number){
    return this.http.delete(API + '?id=' + id);
  }
}
