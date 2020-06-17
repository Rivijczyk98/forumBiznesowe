import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const API = 'http://localhost:8081/api/replays';

@Injectable({
  providedIn: 'root'
})
export class ReplaysService {

  constructor(private http: HttpClient) { }

  
}
