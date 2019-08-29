import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Router } from '@angular/router';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id: any = localStorage.getItem('id');
  product: Product = new Product();
  newStock: number;
  operation: any;
  updateForm: any;

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit() {

    this.operation = localStorage.getItem('operation');
    this.productService.getProductById(this.id).subscribe(product => {
      this.product = product;
    });
  }

  SaveEdit(updateForm: any) {

    if (updateForm.valid) {
      if (this.operation === 'true') {
        this.product.quantity = this.product.quantity + +this.newStock;

        this.productService.updateStock(this.product).subscribe(data => {
          alert('Success');
        });
        this.router.navigate(['list']);

      } else {
        if (this.newStock <= this.product.quantity) {
          this.product.quantity = this.product.quantity + -this.newStock;

          this.productService.updateStock(this.product).subscribe(data => {
            alert('Success');
          });
          this.router.navigate(['list']);
        } else {
          alert('There are not enough stock to sell');
        }

      }
    } else {
      alert('There field with * is mandatory');
    }

  }

}
