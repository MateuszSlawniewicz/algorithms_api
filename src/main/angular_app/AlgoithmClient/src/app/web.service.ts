import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Coordinate } from './model/coordinate.model';

@Injectable({
  providedIn: 'root'
})
export class WebService {

  private MAIN_URL = 'http://localhost:8080';
  constructor(private http: HttpClient) { }


  generateChecksum(number: any) {
    return this.http.post(this.MAIN_URL + '/luhnAlgorithm/checksum', number);
  }
  validate(number: any) {
    return this.http.post(this.MAIN_URL + '/luhnAlgorithm/valid', number);
  }

  generateNearestRoad(coordinates: Coordinate[]) {
    return this.http.post(this.MAIN_URL + '/nna', ({ coordinates: coordinates }));

  }
}
