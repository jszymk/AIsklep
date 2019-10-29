import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {AppService} from '../app.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  constructor(private app: AppService, private formBuilder: FormBuilder, private router: Router) { }

  resetForm: FormGroup;
  error = false;

  ngOnInit() {
    this.resetForm = this.formBuilder.group({
      email : [null, Validators.email]
    });
  }

  onFormSubmit(resetForm: NgForm) {
    this.error = false;
    this.app.resetPassword(resetForm)
      .subscribe(res => {
        this.router.navigateByUrl('reset-password/success', { state: {email: this.resetForm.get('email').value} });
      }, (err) => {
        this.error = true;
      });
  }

}
