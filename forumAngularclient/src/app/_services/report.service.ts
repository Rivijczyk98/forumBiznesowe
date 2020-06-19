import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Report} from '../_model/report';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(http: HttpClient) {
  }

  addReport(report: Report) {
  }
}
