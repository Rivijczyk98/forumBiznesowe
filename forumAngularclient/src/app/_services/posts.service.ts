import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../_model/post';

const API = 'http://localhost:8081/api/posts';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(API + '/all');
  }

  public findById(id: number): Observable<Post> {
    return this.http.get<Post>(API + '?id=' + id);
  }

  public addPost(post: Post) {
    return this.http.post<Post>(API, post);
  }

  public updatePost(post: Post) {
    return this.http.put<Post>(API, post);
  }

  public deletePost(id: number) {
    return this.http.delete(API + '?=' + id);
  }

  public getPostsByCategory(id: number){
    return this.http.get<Post[]>(API + '/category?=' + id);
  }

}
