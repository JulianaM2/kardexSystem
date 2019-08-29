import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Product = new Product();

  constructor(private router: Router, private productService: ProductService) { }

  ngOnInit() {
  }

  SaveProduct(product: Product, addForm: any) {

    if (addForm.valid) {
      this.productService.addNewProduct(this.product).subscribe(data => {
        alert('Success');
        this.router.navigate(['list']);
      });
    } else {
      alert('The field with * are mandatory');
    }

  }

}
