import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HealthService {

  private apiUrl: string = 'http://localhost:8080/health';

  constructor(private http: HttpClient) {}

  // Health check
  getHealthStatus(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
