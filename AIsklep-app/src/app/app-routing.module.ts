import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {RegisterSuccessComponent} from "./register/register-success/register-success.component";
import {ActivateComponent} from "./activate/activate.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {ResetPasswordSuccessComponent} from "./reset-password/reset-password-success/reset-password-success.component";
import {ChangePasswordComponent} from "./change-password/change-password.component";
import {ChangePasswordSuccessComponent} from "./change-password/change-password-success/change-password-success.component";
import {ProductsComponent} from "./products/products.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {ProductOrdersComponent} from "./product-orders/product-orders.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'products',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {title: 'Logowanie'}
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {title: 'Rejestracja'}
  },
  {
    path: 'register/success',
    component: RegisterSuccessComponent,
    data: {title: 'Rejestracja'}
  },
  {
    path: 'activate',
    component: ActivateComponent,
    data: {title: 'Aktywacja'}
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
    data: {title: 'Resetuj hasło'}
  },
  {
    path: 'reset-password/success',
    component: ResetPasswordSuccessComponent,
    data: {title: 'Resetuj hasło'}
  },
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    data: {title: 'Zmień hasło'}
  },
  {
    path: 'change-password/success',
    component: ChangePasswordSuccessComponent,
    data: {title: 'Zmień hasło'}
  },
  {
    path: 'products',
    component: ProductsComponent,
    data: {title: 'Asortyment'}
  },
  {
    path: 'product-details',
    component: ProductDetailsComponent,
    data: {title: 'Asortyment'}
  },
  {
    path: 'shopping-cart',
    component: ShoppingCartComponent,
    data: {title: 'Koszyk'}
  },
  {
    path: 'product-orders',
    component: ProductOrdersComponent,
    data: {title: 'Zamówienia'}
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
