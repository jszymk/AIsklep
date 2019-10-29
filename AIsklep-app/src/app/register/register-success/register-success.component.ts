import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/index';
import {map} from 'rxjs/internal/operators';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-register-success',
  templateUrl: './register-success.component.html',
  styleUrls: ['./register-success.component.scss']
})
export class RegisterSuccessComponent implements OnInit {

  emailField = 'email';
  email: string;

  constructor(public route: ActivatedRoute, private router: Router) {
    const state = this.router.getCurrentNavigation().extras.state;
    this.email = state[this.emailField];
  }

  ngOnInit() {
  }

}
