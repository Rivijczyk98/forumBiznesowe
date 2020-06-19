import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../_model/category';

const API = 'http://localhost:8081/categories';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Category[]> {
    return this.http.get<Category[]>(API + '/all');
  }

  public findById(id: number): Observable<Category> {
    return this.http.get<Category>(API + '/find?id=' + id);
  }

  public addCategory(category: any) {
    return this.http.post<Category>(API + '/add', category);
  }

  public updateCategory(category: any) {
    console.log(category);
    return this.http.put<Category>(API + '/edit', category);
  }

  public deleteCategory(id: number) {
    console.log('url:', API + '/delete?id=' + id);
    return this.http.delete(API + '/delete?id=' + id);
  }

}
