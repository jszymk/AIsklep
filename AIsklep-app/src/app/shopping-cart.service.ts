
import {EventEmitter, Injectable} from '@angular/core';
import {ProductDTO} from './dto/product-dto';

@Injectable()
export class ShoppingCartService {
  public $cartChanged: EventEmitter<ProductDTO> = new EventEmitter();
}
