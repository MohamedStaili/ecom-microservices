import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {DatePipe, NgIf, NgFor, DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-order-details',
  imports: [
    NgIf,
    DatePipe,
    NgFor,
    DecimalPipe
  ],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent {
  order : any
  orderId!: number
  constructor(private http: HttpClient, private router: ActivatedRoute) {
    this.orderId=router.snapshot.params['orderId'];
  }
  ngOnInit(): void {
    this.http.get(`http://localhost:9999/order-service/fullOrder/${this.orderId}`).subscribe({
      next: data => {
        this.order = data;
      },
      error: err => {}
    })
  }

}
