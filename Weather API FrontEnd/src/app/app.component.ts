import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'weather-frontend';
  isLoggedIn: boolean = false;
  username: string = '';
  password: string = '';

  // Simulated login
  login() {
    if (this.username === 'admin' && this.password === 'admin') {
      this.isLoggedIn = true;
      localStorage.setItem('isLoggedIn', 'true');
    } else {
      alert('Invalid credentials!');
    }
  }

  // Logout
  logout() {
    this.isLoggedIn = false;
    localStorage.removeItem('isLoggedIn');
    this.username = '';
    this.password = '';
  }

  ngOnInit() {
    // Check session
    this.isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
  }
}
