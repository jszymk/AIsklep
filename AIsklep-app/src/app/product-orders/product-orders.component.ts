import { Component, OnInit } from '@angular/core';
import {ProductOrderDTO} from '../dto/product-order-dto';
import {AppService} from '../app.service';

@Component({
  selector: 'app-product-orders',
  templateUrl: './product-orders.component.html',
  styleUrls: ['./product-orders.component.scss']
})
export class ProductOrdersComponent implements OnInit {

  constructor(private api: AppService) { }

  productOrders: ProductOrderDTO[];

  ngOnInit() {
    this.api.getOrders().subscribe(res => {
      this.productOrders = res;
    });
  }

  removeOrder(id: number) {
    this.api.removeOrder(id).subscribe(r => {
      this.api.getOrders().subscribe(res => {
        this.productOrders = res;
      });
    });
  }

  getPriceSum(productOrder: ProductOrderDTO) {
    return productOrder.products.map(p => p.product.price * p.quantity).reduce((a, b) => a + b);
  }
}
