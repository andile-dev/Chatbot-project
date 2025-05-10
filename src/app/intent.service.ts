import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IntentService {

  private apiUrl = 'http://localhost:8082/api/intent';  // URL of your Spring Boot backend

  constructor(private http: HttpClient) { }

  getIntent(query: string): Observable<string> {
    let params = new HttpParams().set('query', query);
    return this.http.get<string>(this.apiUrl, { params });
  }
}
