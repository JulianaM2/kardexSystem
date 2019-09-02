import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/service/product.service';
import { Product } from 'src/app/model/product';
import { ToastrService} from 'ngx-toastr';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit {

  product: Product = new Product();

  constructor(private router: Router, private productService: ProductService, ) { }

  ngOnInit() {
    this.product.name = 'T-Shirt';
  }

  SaveProduct(product: Product, addForm: any) {

    if (addForm.valid) {
      this.productService.addNewProduct(this.product).subscribe(data => {
        Swal.fire(
          'Success',
          'New product added successfuly',
          'success'
        );
        this.router.navigate(['list']);
      });
    }

  }

  Cancel() {
    this.router.navigate(['list']);
  }

}
