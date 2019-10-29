import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductDTO} from '../dto/product-dto';
import {ShoppingCartService} from '../shopping-cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  constructor(public route: ActivatedRoute, private router: Router, private cartService: ShoppingCartService) {
    const state = this.router.getCurrentNavigation().extras.state;
    const productField = 'product';
    this.product = state[productField];
  }

  product: ProductDTO;
  quantity = 1;

  ngOnInit() {
  }

  goTo(path: string) {
    this.router.navigate([path]);
  }

  addToCart() {
    if (JSON.parse(sessionStorage.getItem('shoppingCart')) == null) {
      const cart: any = [];
      cart.push({product: this.product, quantity: this.quantity});
      sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
    } else {
      const cart: any = JSON.parse(sessionStorage.getItem('shoppingCart'));
      let index = -1;
      for (let i = 0; i < cart.length; i++) {
        const item: any = cart[i];
        if (item.product.id === this.product.id) {
          index = i;
          break;
        }
      }
      if (index === -1) {
        cart.push({product: this.product, quantity: this.quantity});
        sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
      } else {
        const item: any = cart[index];
        item.quantity += this.quantity;
        cart[index] = item;
        sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
      }
    }
    this.cartService.$cartChanged.emit(this.product);
    this.router.navigate(['']);
  }
}
