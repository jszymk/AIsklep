import {Component, Host, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {AppComponent} from '../app.component';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private api: AppService, private formBuilder: FormBuilder, private router: Router) {
  }

  parent: AppComponent;
  loginForm: FormGroup;
  error = false;

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email : [null, Validators.email],
      password : [null, Validators.required]
    });
  }

  onFormSubmit(loginForm: NgForm) {
    this.error = false;
    this.api.login(loginForm)
      .subscribe(res => {
        console.log(res);
        if (res.token) {
          sessionStorage.setItem('token', res.token);
          location.assign('');
        }
      }, (err) => {
          this.error = true;
      });
  }

  goTo(path: string) {
    this.router.navigate([path]);
  }

}
