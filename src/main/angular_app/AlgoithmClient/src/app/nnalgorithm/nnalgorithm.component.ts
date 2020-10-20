import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { stringify } from 'querystring';
import { Subscription } from 'rxjs';
import { Coordinate } from '../model/coordinate.model';
import { WebService } from '../web.service';

@Component({
  selector: 'app-nnalgorithm',
  templateUrl: './nnalgorithm.component.html',
  styleUrls: ['./nnalgorithm.component.css']
})
export class NnalgorithmComponent implements OnInit, OnDestroy {
  dynamicForm: FormGroup;
  submitted = false;
  coordinates: Coordinate[] = [];
  results: string[] = [];
  distance: string;
  subscription: Subscription;
  constructor(private formBuilder: FormBuilder, private webService: WebService) { }

  ngOnInit() {
    this.dynamicForm = this.formBuilder.group({
      numberOfPlaces: ['2', Validators.required],
      places: new FormArray([])
    });
  }

  get f() { return this.dynamicForm.controls; }
  get t() { return this.f.places as FormArray; }

  onSubmit() {
    for (let pl of (this.f.places as FormArray).value) {
      let cord = new Coordinate(pl.place, pl.lat, pl.lon);
      this.coordinates.push(cord);
    }
    this.subscription = this.webService.generateNearestRoad(this.coordinates)
      .subscribe(response => {
        let coords = response.coordinates as Coordinate[];
        for(let i of coords){
         this.results.push(i.name);
        }
        this.distance = response.distance
    });

}

onChangePlace(e) {
  const numberOfPlaces = e.target.value || 0;
  if (this.t.length < numberOfPlaces) {
    for (let i = this.t.length; i < numberOfPlaces; i++) {
      this.t.push(this.formBuilder.group({
        place: ['', Validators.required],
        lat: ['', [Validators.required]],
        lon: ['', [Validators.required]]
      }));
    }
  } else {
    for (let i = this.t.length; i >= numberOfPlaces; i--) {
      this.t.removeAt(i);
    }
  }
}


ngOnDestroy(){
  this.subscription.unsubscribe();
}
}
