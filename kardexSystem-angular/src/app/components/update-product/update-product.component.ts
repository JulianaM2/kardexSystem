import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Router } from '@angular/router';
import { Product } from 'src/app/model/product';
import Swal from 'sweetalert2';

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
      } else {
        this.product.quantity = this.product.quantity + -this.newStock;
      }
      this.productService.updateStock(this.product).subscribe(data => {
        if (this.operation === 'true') {
          Swal.fire(
            'Success',
            'Stock updated successfuly',
            'success'
          );
        } else {
          Swal.fire(
            'Success',
            'Stock sold successfuly',
            'success'
          );
        }
        this.router.navigate(['list']);
      });
    }
  }

  Cancel() {
    this.router.navigate(['list']);
  }
}
