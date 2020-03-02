import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {ROUTES} from './app.routes';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {RouterModule} from '@angular/router';
import { UserDetailsComponent } from './user-components/user-details/user-details.component';
import { ProductDetailComponent } from './product-components/product-detail/product-detail.component';
import { LoginComponent } from './login/login.component';
import { UserAlterComponent } from './user-components/user-alter/user-alter.component';
import { UserRegisterComponent } from './user-components/user-register/user-register.component';
import { UserDeleteComponent } from './user-components/user-delete/user-delete.component';
import { UserOrdersComponent } from './user-components/user-orders/user-orders.component';
import { HeaderComponent } from './header/header.component';
import { ProductMarketPageComponent } from './product-components/product-market-page/product-market-page.component';
import { FooterComponent } from './footer/footer.component';
import { AddressRegisterComponent } from './address-components/address-register/address-register.component';
import { AddressAlterComponent } from './address-components/address-alter/address-alter.component';
import { AddressDeleteComponent } from './address-components/address-delete/address-delete.component';
import { AddressListComponent } from './address-components/address-list/address-list.component';
import { CreditcardRegisterComponent } from './buy-components/creditcard/creditcard-register/creditcard-register.component';
import { CreditcardAlterComponent } from './buy-components/creditcard/creditcard-alter/creditcard-alter.component';
import { CreditcardDeleteComponent } from './buy-components/creditcard/creditcard-delete/creditcard-delete.component';
import { CreditcardListComponent } from './buy-components/creditcard/creditcard-list/creditcard-list.component';
import { UserPasswordAlterComponent } from './user-components/user-password-alter/user-password-alter.component';
import { CartComponent } from './buy-components/cart/cart.component';
import { OrderResumeComponent } from './buy-components/order-resume/order-resume.component';
import { PaymentPageComponent } from './buy-components/payment-page/payment-page.component';
import {FormsModule} from '@angular/forms';
import {AuthService} from './login/auth.service';
import { BuyConfirmComponent } from './buy-components/buy-confirm/buy-confirm.component';
import { CardRegisterComponent } from './admin-components/cards/card-register/card-register.component';
import { CardListComponent } from './admin-components/cards/card-list/card-list.component';
import { CardAlterComponent } from './admin-components/cards/card-alter/card-alter.component';
import { CardDeleteComponent } from './admin-components/cards/card-delete/card-delete.component';
import { AdminPageComponent } from './admin-components/admin-page/admin-page.component';
import { CardActiveManagemantComponent } from './admin-components/cards/card-active-managemant/card-active-managemant.component';
import { CardListInactiveComponent } from './admin-components/cards/card-list-inactive/card-list-inactive.component';
import { CardActiveComponent } from './admin-components/cards/card-active/card-active.component';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailsComponent,
    ProductDetailComponent,
    LoginComponent,
    UserAlterComponent,
    UserRegisterComponent,
    UserDeleteComponent,
    UserOrdersComponent,
    HeaderComponent,
    ProductMarketPageComponent,
    FooterComponent,
    AddressRegisterComponent,
    AddressAlterComponent,
    AddressDeleteComponent,
    AddressListComponent,
    CreditcardRegisterComponent,
    CreditcardAlterComponent,
    CreditcardDeleteComponent,
    CreditcardListComponent,
    UserPasswordAlterComponent,
    CartComponent,
    OrderResumeComponent,
    PaymentPageComponent,
    BuyConfirmComponent,
    CardRegisterComponent,
    CardListComponent,
    CardAlterComponent,
    CardDeleteComponent,
    AdminPageComponent,
    CardActiveManagemantComponent,
    CardListInactiveComponent,
    CardActiveComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(ROUTES),
    FormsModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
