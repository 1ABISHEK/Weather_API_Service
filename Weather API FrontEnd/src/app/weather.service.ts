import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private apiUrl: string = 'http://localhost:8080/weather';

  constructor(private http: HttpClient) {}

  // Current weather
  getCurrentWeather(location: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/current?location=${location}`);
  }

  // Forecast
  getForecast(location: string, days: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/forecast?location=${location}&days=${days}`);
  }
}
