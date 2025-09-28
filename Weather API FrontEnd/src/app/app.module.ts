import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchLocationComponent } from './search-location/search-location.component';
import { LocationListComponent } from './location-list/location-list.component';
import { CurrentWeatherComponent } from './current-weather/current-weather.component';
import { ForecastWeatherComponent } from './forecast-weather/forecast-weather.component';
import { FormsModule } from '@angular/forms';            
import { HttpClientModule } from '@angular/common/http';
import { HealthComponent } from './health/health.component';
import { LoginComponent } from './login/login.component'; 


@NgModule({
  declarations: [
    AppComponent,
    SearchLocationComponent,
    LocationListComponent,
    CurrentWeatherComponent,
    ForecastWeatherComponent,
    HealthComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
