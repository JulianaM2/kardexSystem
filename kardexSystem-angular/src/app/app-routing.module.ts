import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddProductComponent } from './components/add-product/add-product.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { ProductsComponent } from './components/products/products.component';

const routes: Routes = [
  { path: 'addNew', component: AddProductComponent  },
  { path: 'update', component: UpdateProductComponent },
  { path: 'list', component: ProductsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
