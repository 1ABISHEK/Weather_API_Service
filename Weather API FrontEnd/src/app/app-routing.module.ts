import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CurrentWeatherComponent } from './current-weather/current-weather.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
   { path: 'login', component: LoginComponent },
  { path: 'current-weather', component: CurrentWeatherComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 


}
