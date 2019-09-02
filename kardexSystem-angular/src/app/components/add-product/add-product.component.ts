import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
  styles: [`
    .ng-invalid. ng-touched{
      border 2px solid red;
    }
  `]
})
export class AddProductComponent implements OnInit {

  product: Product = new Product();

  constructor(private router: Router, private productService: ProductService) { }

  ngOnInit() {
    this.product.name = 'T-Shirt';
  }

  SaveProduct(product: Product, addForm: any) {

    if (addForm.valid) {
      this.productService.addNewProduct(this.product).subscribe(data => {
        alert('Success');
        this.router.navigate(['list']);
      });
    }

  }

}
