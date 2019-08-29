import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
    providedIn: 'root'
})

export class ProductService {

    private baseUrl = 'http://localhost:8888/kardex/product';

    constructor(private http: HttpClient) { }

    getProducts(): Observable<any> {
        return this.http.get(this.baseUrl);
    }

    addNewProduct(product: Product): Observable<any> {
        return this.http.post(this.baseUrl + '/add', product);
    }

    getProductById(id: number): Observable<any> {
        return this.http.get(this.baseUrl + '/' + id);
    }

    updateStock(product: Product): Observable<any> {
        return this.http.put(this.baseUrl + '/update/' + product.id, product);
    }
}
