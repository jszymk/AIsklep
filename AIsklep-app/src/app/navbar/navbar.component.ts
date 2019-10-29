import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AppService} from '../app.service';
import {ProductCategoryDTO} from '../dto/product-category-dto';
import {ShoppingCartService} from '../shopping-cart.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router, private api: AppService, private cartService: ShoppingCartService) { }

  user: {
    email: string;
    phone: string;
    address: string;
  };

  shoppingCartTotal: number;

  ngOnInit() {
    this.api.user()
      .subscribe(usr => this.user = usr);
    this.loadCart();
    this.cartService.$cartChanged.subscribe(prod => this.loadCart());
  }

  goTo(path: string) {
    this.router.navigate([path]);
  }

  logout() {
    this.api.logout().subscribe(res => {
      sessionStorage.removeItem('token');
      location.assign('');
    });
  }

  loadCart() {
    const cart: any = JSON.parse(sessionStorage.getItem('shoppingCart'));
    if (cart != null) {
      let cartTotal = 0;
      for (let i = 0; i < cart.length; i++) {
        cartTotal += cart[i].quantity * cart[i].product.price;
      }
      this.shoppingCartTotal = cartTotal;
    } else {
      this.shoppingCartTotal = 0;
    }
  }

}
