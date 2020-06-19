import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../_model/post';

const API = 'http://localhost:8081/posts';

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

  public addPost(post: Post, idUser: number, categoryName: string) {
    return this.http.post<Post>(API + '?idUser=' + idUser + '&categoryName=' + categoryName, post);
  }

  public updatePost(post: Post) {
    return this.http.put<Post>(API, post);
  }

  public approvePost(post: Post) {
    return this.http.put<Post>(API + '/approve', post);
  }

  public deletePost(id: number) {
    return this.http.delete(API + '?id=' + id);
  }

  public getPostsByCategory(name: string){
    return this.http.get<Post[]>(API + '/category?name=' + name);
  }

  public getPendedPosts(){
    return this.http.get<Post[]>(API + '/category-pended');
  }

}
