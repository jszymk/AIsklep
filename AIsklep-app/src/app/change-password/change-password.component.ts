import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private api: AppService, private formBuilder: FormBuilder, private router: Router) {
  }

  changePasswordForm: FormGroup;
  error = false;

  ngOnInit() {
    this.changePasswordForm = this.formBuilder.group({
      password : [null, Validators.required],
      oldPassword : [null, Validators.required],
      confirmPassword : [null, Validators.pattern]
    });
  }

  onFormSubmit(changePasswordForm: NgForm) {
    this.error = false;
    this.api.changePassword(changePasswordForm)
      .subscribe(res => {
        this.router.navigate(['change-password/success']);
      }, (err) => {
        this.error = true;
      });
  }

}
