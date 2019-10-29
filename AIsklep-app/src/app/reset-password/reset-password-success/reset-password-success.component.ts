import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-reset-password-success',
  templateUrl: './reset-password-success.component.html',
  styleUrls: ['./reset-password-success.component.scss']
})
export class ResetPasswordSuccessComponent implements OnInit {

  emailField = 'email';
  email: string;

  constructor(public route: ActivatedRoute, private router: Router) {
    const state = this.router.getCurrentNavigation().extras.state;
    this.email = state[this.emailField];
  }

  ngOnInit() {
  }
}
