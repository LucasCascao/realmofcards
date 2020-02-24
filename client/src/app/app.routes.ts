import {Routes} from '@angular/router';

import {HomeComponent} from './home/home.component';
import {UserDetailsComponent} from './user-details/user-details.component';
import {ProductDetailComponent} from './home/product-detail/product-detail.component';
import {LoginComponent} from './login/login.component';

export const ROUTES: Routes = [
  // {
  //   path: '', component: HomeComponent,
  //   children: [
  //     {path: 'product-detail', component: ProductDetailComponent}
  //   ]
  // },
  {path: '', component: HomeComponent},
  {path: 'user-details', component: UserDetailsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'product-detail', component: ProductDetailComponent}
];
