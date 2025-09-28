import { Component } from '@angular/core';
import { HealthService } from '../health.service';

@Component({
  selector: 'app-health',
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.css']
})
export class HealthComponent {
healthStatus: any = {};

  constructor(private healthService: HealthService) {}

  ngOnInit() {
    this.healthService
      .getHealthStatus()
      .subscribe((status) => (this.healthStatus = status));
    console.log(this.healthStatus);
  }
}
