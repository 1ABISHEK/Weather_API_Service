import { Component, Input } from '@angular/core';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-forecast-weather',
  templateUrl: './forecast-weather.component.html',
  styleUrls: ['./forecast-weather.component.css']
})
export class ForecastWeatherComponent {

  forecast: any = {};         // object with forecasts inside
  location: string = '';
  days: number = 0;
  submitted: boolean = false;

  constructor(private weatherService: WeatherService) {}

  getForecast() {
    if (this.location.trim() === '' || this.days <= 0) {
      return;
    }

    this.weatherService
      .getForecast(this.location, this.days)
      .subscribe((data) => this.forecast = data);

    this.submitted = true;
    console.log(this.forecast);
  }
}
