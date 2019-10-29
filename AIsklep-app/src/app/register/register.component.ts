import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AppService} from '../app.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private app: AppService, private http: HttpClient, private router: Router) { }

  registerForm: FormGroup;
  error = false;

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email : [null, [Validators.required, Validators.email]],
      address : [null, Validators.required],
      phone : [null],
      password : [null, Validators.required],
      repeatPassword : [null, Validators.pattern]
    });
  }

  onFormSubmit(form: NgForm) {
    this.error = false;
    this.app.register(form)
      .subscribe(res => {
        this.router.navigateByUrl('register/success', { state: {email: this.registerForm.get('email').value} });
      }, (err) => {
        this.error = true;
      });
  }

}
