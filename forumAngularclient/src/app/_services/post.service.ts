import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Post} from '../_model/post';

const API = 'http://localhost:8081/posts';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(API + '/all');
  }

  public findById(id: number): Observable<Post> {
    return this.http.get<Post>(API + '?id=' + id);
  }

  public addPost(post: Post, idUser: number, categoryName: string) {
    return this.http.post<Post>(API + 'add?idUser=' + idUser + '&categoryName=' + categoryName, post);
  }

  public updatePost(post: Post) {
    return this.http.put<Post>(API + '/update',
      {
        id: post.id,
        author: post.author.id,
        category: post.category.id,
        title: post.title,
        text: post.text,
        isObserved: post.isObserved,
        status: post.status.name,
        postedDate: post.postedDate
      });
  }

  public deletePost(id: number) {
    return this.http.delete(API + 'delete?id=' + id);
  }

  public getPostsByCategory(id: number) {
    return this.http.get<Post[]>(API + '/category?id=' + id);
  }


  public changeIsObserved(isOb: boolean, post: Post) {
    return this.http.patch<Post>(API + '/observed?id=' + isOb, post);
  }

}
