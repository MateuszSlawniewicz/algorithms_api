import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { WebService } from '../web.service';

@Component({
  selector: 'app-luhn',
  templateUrl: './luhn.component.html',
  styleUrls: ['./luhn.component.css']
})
export class LuhnComponent implements OnInit, OnDestroy {
  form: FormGroup;
  output: string;
  subscription: Subscription;

  constructor(private webService: WebService) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit() {
    this.form = new FormGroup({
      number: new FormControl('', [
        Validators.pattern('^[0-9]*$')
      ])
    });
  }


  valid() {
    console.log(this.form.value.number);
    this.subscription = this.webService.validate({ number: this.form.value.number })
      .subscribe(a => {
        if (a.valid == true) {
          this.output = 'Your number is valid';
        } else {
          this.output = 'Your number is invalid';
        }

      });
  }


  generateChecksum() {
    this.subscription = this.webService.generateChecksum({ number: this.form.value.number })
      .subscribe(a => { this.output = 'Checksum is: ' + a.checkSum });
  }
}
