import { Component, EventEmitter, Output } from '@angular/core';
import { LocationService } from '../location.service';

@Component({
  selector: 'app-search-location',
  templateUrl: './search-location.component.html',
  styleUrls: ['./search-location.component.css']
})
export class SearchLocationComponent {
query: string = '';
  locationResults: any[] = [];
  showDropdown: boolean = false;

  // ✅ Emit selected location string to parent
  @Output() locationSelected = new EventEmitter<string>();

  constructor(private locationService: LocationService) {}

  // 🔍 Search backend
  searchLocation() {
    if (this.query.trim().length < 2) {
      this.locationResults = [];
      this.showDropdown = false;
      return;
    }

    this.locationService.searchLocations(this.query).subscribe((data) => {
      this.locationResults = data;
      this.showDropdown = true;
    });
  }

  // ✅ Handle selection
  selectLocation(loc: any) {
    const formatted =
      loc.name + (loc.state ? ', ' + loc.state : '') + ', ' + loc.country;
    this.query = formatted;
    this.showDropdown = false;
    this.locationSelected.emit(formatted);
  }
  
}
