import { Component, OnInit } from '@angular/core';
import {ProductDTO} from '../dto/product-dto';
import {ShoppingCartService} from "../shopping-cart.service";
import {AppService} from "../app.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {

  constructor(private cartService: ShoppingCartService, private api: AppService, private router: Router) { }

  items: {product: ProductDTO, quantity: number}[];
  isLoggedIn = sessionStorage.getItem('token') != null;

  ngOnInit() {
    this.items = JSON.parse(sessionStorage.getItem('shoppingCart'));
    this.cartService.$cartChanged.subscribe(prod => this.items = JSON.parse(sessionStorage.getItem('shoppingCart')));
  }

  removeFromCart(toDelete: {product: ProductDTO, quantity: number}) {
    const cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
    let index = -1;
    for (let i = 0; i < cart.length; i++) {
      const item: any = cart[i];
      if (item.product.id === toDelete.product.id) {
        index = i;
        break;
      }
    }
    if (index !== -1) {
      cart.splice(index, 1);
      sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
    }
    this.cartService.$cartChanged.emit(toDelete.product);
  }

  orderProducts() {
    this.api.orderProducts(this.items.map(i => ({productId: i.product.id, quantity: i.quantity}))).subscribe(() => {
      sessionStorage.setItem('shoppingCart', null);
      this.cartService.$cartChanged.emit(null);
      this.router.navigate(['']);
    });
  }

  getPriceSum(products: {product: ProductDTO, quantity: number}[]) {
    return products.map(p => p.product.price * p.quantity).reduce((a, b) => a + b);
  }

}
