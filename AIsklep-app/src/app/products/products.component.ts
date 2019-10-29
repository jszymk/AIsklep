import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ProductDTO} from '../dto/product-dto';
import {AppService} from '../app.service';
import {DomSanitizer} from '@angular/platform-browser';
import {ProductCategoryDTO} from '../dto/product-category-dto';
import {Router} from "@angular/router";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  constructor(private api: AppService, private sanitizer: DomSanitizer, private router: Router) { }

  products: ProductDTO[];
  categories: ProductCategoryDTO[];

  categoryId: number = null;
  searchPhrase: string = null;

  ngOnInit() {
    this.fetchProducts();
    this.api.fetchCategories().subscribe(cat => this.categories = cat);
  }

  fetchProducts() {
    this.api.searchProducts({categoryId: this.categoryId, searchPhrase: this.searchPhrase}).subscribe(res => {
      this.products = res;
      this.products.forEach(p => {
        if (p.imagePath != null) {
          this.getImageFromService(p);
        } else {
          p.isImageLoading = true;
        }
      });
    });
  }

  createImageFromBlob(image: Blob, product: ProductDTO) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      product.image = this.sanitizer.bypassSecurityTrustUrl(reader.result.toString());
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(product: ProductDTO) {
    product.isImageLoading = true;
    this.api.image(product.imagePath).subscribe(data => {
      this.createImageFromBlob(data, product);
      product.isImageLoading = false;
    }, error => {
      product.isImageLoading = false;
      console.log(error);
    });
  }

  productDetails(product: ProductDTO) {
    this.router.navigateByUrl('product-details', { state: {product: product} });
  }

}
