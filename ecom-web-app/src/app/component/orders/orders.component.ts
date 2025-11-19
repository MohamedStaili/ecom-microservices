import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {DatePipe, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-orders',
  imports: [
    NgForOf,
    NgIf,
    DatePipe
  ],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent {
  orders : any
  customerId! : number
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.customerId= route.snapshot.params['customerId'];
  }
  ngOnInit(): void {
    this.http.get(`http://localhost:9999/order-service/orders/search/byCustomerId?customerId=${this.customerId}&projection=fullOrder`).subscribe({
      next: data => {
        this.orders = data;
      },
      error: err => {}
    })
  }

  getOrderDetails(c: any) {
    this.router.navigateByUrl('order-details/'+c.id);

  }
}
