import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ProductService } from 'src/app/service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
  styles: [`
    .ng-invalid{
      border: 1px solid red;
    }
  `]
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }

  AddSotck(product: Product) {
    localStorage.setItem('id', product.id.toString());
    localStorage.setItem('operation', 'true');
    this.router.navigate(['update']);
  }

  SellStock(product: Product) {
    localStorage.setItem('id', product.id.toString());
    localStorage.setItem('operation', 'false');
    this.router.navigate(['update']);
  }
}
