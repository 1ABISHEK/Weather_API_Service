import { Component } from '@angular/core';
import { WeatherService } from '../weather.service';
import { LocationService } from '../location.service';

@Component({
  selector: 'app-current-weather',
  templateUrl: './current-weather.component.html',
  styleUrls: ['./current-weather.component.css']
})
export class CurrentWeatherComponent {

  location: string = '';
  currentWeather: any = null;
  forecast: any[] = [];
  submitted = false;
  today: Date = new Date();

  // 🔍 Location search
  locationResults: any[] = [];
  showDropdown: boolean = false;

  // 🌍 Map marker position (% values)
  markerX: number | null = null;
  markerY: number | null = null;

  // 🌍 Store selected lat/lon for tooltip
  selectedLat: number | null = null;
  selectedLon: number | null = null;

  constructor(
    private weatherService: WeatherService,
    private locationService: LocationService
  ) {}

  // 🔍 Live location search
  searchLocation() {
    if (this.location.trim().length < 2) {
      this.locationResults = [];
      this.showDropdown = false;
      return;
    }
    this.locationService.searchLocations(this.location).subscribe((data) => {
      this.locationResults = data;
      this.showDropdown = true;
    });
  }

  // ✅ Select location + update map marker + save lat/lon
  selectLocation(loc: any) {
    this.location = loc.name + (loc.state ? ', ' + loc.state : '') + ', ' + loc.country;
    this.showDropdown = false;

    // Save lat/lon for tooltip
    this.selectedLat = loc.latitude;
    this.selectedLon = loc.longitude;

    // Set marker position on map
    this.setMarkerPosition(loc.latitude, loc.longitude);

    this.getWeather();
  }

  // 🌦️ Get Weather + Forecast
  getWeather() {
    if (this.location.trim() === '') return;

    this.weatherService.getCurrentWeather(this.location).subscribe((data) => {
      this.currentWeather = data;
      this.submitted = true;
    });

    this.weatherService.getForecast(this.location, 7).subscribe((data) => {
      this.forecast = data.forecasts || data;
    });
  }

  // ✅ FontAwesome icons
  getWeatherIcon(temp: number, condition: string): string {
    if (!condition) return 'fas fa-cloud-sun';
    const c = condition.toLowerCase();

    if (c.includes('rain')) return 'fas fa-cloud-showers-heavy';
    if (c.includes('storm')) return 'fas fa-bolt';
    if (c.includes('snow')) return 'fas fa-snowflake';
    if (c.includes('cloud')) return 'fas fa-cloud';
    if (temp >= 30) return 'fas fa-sun';
    if (temp < 20) return 'fas fa-temperature-low';
    return 'fas fa-cloud-sun';
  }

  // ✅ Get short day name
  getDayName(dateStr: string): string {
    const date = new Date(dateStr);
    return date.toLocaleDateString('en-US', { weekday: 'short' }).toUpperCase();
  }

  // 🌍 Convert lat/lon → % for world map
  private setMarkerPosition(lat: number, lon: number) {
    this.markerX = ((lon + 180) / 360) * 100; 
    this.markerY = ((90 - lat) / 180) * 100;   
  }

  getFormattedTime(unixTime: number): string {
    if (!unixTime) return '';
    const date = new Date(unixTime * 1000);
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: true });
  }

  copyCoords() {
    if (this.selectedLat && this.selectedLon) {
      const coords = `Lat: ${this.selectedLat}, Lon: ${this.selectedLon}`;
      navigator.clipboard.writeText(coords);
      alert('Coordinates copied: ' + coords);
    }
  }

  // 🎨 Class for forecast card color based on condition
  getForecastClass(condition: string): string {
    if (!condition) return 'default-weather';
    const c = condition.toLowerCase();

    if (c.includes('rain') || c.includes('storm')) return 'rainy-weather';
    if (c.includes('cloud')) return 'cloudy-weather';
    if (c.includes('snow')) return 'snow-weather';
    if (c.includes('sun') || c.includes('clear') || c.includes('hot')) return 'sunny-weather';
    
    return 'default-weather';
  }

  // 🎨 Class for right-panel card color (same as forecast)
  getPanelClass(): string {
    if (!this.currentWeather || !this.currentWeather.condition) return 'default-weather';
    return this.getForecastClass(this.currentWeather.condition);
  }
}
