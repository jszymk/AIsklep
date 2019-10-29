import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AppService} from '../app.service';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.scss']
})
export class ActivateComponent implements OnInit {

  tokenField = 'token';
  email: string;
  ready = false;

  constructor(public route: ActivatedRoute, private router: Router, private api: AppService) {
    this.route.queryParams.subscribe(params => {
      const token = params[this.tokenField];
      this.api.activate(token).subscribe(res => {
        const emailField = 'email';
        this.email = res[emailField];
        this.ready = true;
      });
    });
  }

  ngOnInit() {
  }

}
