import { Routes } from '@angular/router';
import {ProductsComponent} from './component/products/products.component';
import {CustomersComponent} from './component/customers/customers.component';
import {OrdersComponent} from './component/orders/orders.component';
import {OrderDetailsComponent} from './component/order-details/order-details.component';

export const routes: Routes = [
  {path: "products", component: ProductsComponent},
  {path: "customers", component: CustomersComponent},
  {path: "orders/:customerId", component: OrdersComponent},
  {path: "order-details/:orderId", component: OrderDetailsComponent},
];
