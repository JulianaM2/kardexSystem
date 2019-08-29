import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'kardexSystem';

  constructor(private router: Router) {}

  AddNew() {
    this.router.navigate(['addNew']);
  }

  ListProducts() {
    this.router.navigate(['list']);
  }

}
