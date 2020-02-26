import {Routes} from '@angular/router';

import {UserDetailsComponent} from './user-components/user-details/user-details.component';
import {ProductDetailComponent} from './product-components/product-detail/product-detail.component';
import {LoginComponent} from './login/login.component';
import {UserAlterComponent} from './user-components/user-alter/user-alter.component';
import {UserRegisterComponent} from './user-components/user-register/user-register.component';
import {UserDeleteComponent} from './user-components/user-delete/user-delete.component';
import {ProductMarketPageComponent} from './product-components/product-market-page/product-market-page.component';
import {UserOrdersComponent} from './user-components/user-orders/user-orders.component';
import {AddressRegisterComponent} from './address-components/address-register/address-register.component';
import {AddressAlterComponent} from './address-components/address-alter/address-alter.component';
import {AddressDeleteComponent} from './address-components/address-delete/address-delete.component';
import {AddressListComponent} from './address-components/address-list/address-list.component';
import {CreditcardRegisterComponent} from './buy-components/creditcard-register/creditcard-register.component';
import {CreditcardAlterComponent} from './buy-components/creditcard-alter/creditcard-alter.component';
import {CreditcardDeleteComponent} from './buy-components/creditcard-delete/creditcard-delete.component';
import {CreditcardListComponent} from './buy-components/creditcard-list/creditcard-list.component';

export const ROUTES: Routes = [
  // {
  //   path: '', component: HomeComponent,
  //   children: [
  //     {path: 'product-detail', component: ProductDetailComponent}
  //   ]
  // },
  {path: '', component: ProductMarketPageComponent},
  {path: 'user-details', component: UserDetailsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'product-detail', component: ProductDetailComponent},
  {path: 'user-alter', component: UserAlterComponent},
  {path: 'user-register', component: UserRegisterComponent},
  {path: 'user-delete', component: UserDeleteComponent},
  {path: 'user-orders', component: UserOrdersComponent},
  {path: 'address-register', component: AddressRegisterComponent},
  {path: 'address-alter', component: AddressAlterComponent},
  {path: 'address-delete', component: AddressDeleteComponent},
  {path: 'address-list', component: AddressListComponent},
  {path: 'creditcard-register', component: CreditcardRegisterComponent},
  {path: 'creditcard-alter', component: CreditcardAlterComponent},
  {path: 'creditcard-delete', component: CreditcardDeleteComponent},
  {path: 'creditcard-list', component: CreditcardListComponent}
];
