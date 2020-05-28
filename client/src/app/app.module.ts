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
import { ClientListComponent } from './admin-components/clients/client-list/client-list.component';
import { ClientActiveComponent } from './admin-components/clients/client-active/client-active.component';
import { ClientInactiveComponent } from './admin-components/clients/client-inactive/client-inactive.component';
import { ClientInactiveListComponent } from './admin-components/clients/client-inactive-list/client-inactive-list.component';
import { ProductTradeComponent } from './user-components/product-trade/product-trade.component';
import { ProductGenerateTradeCodeComponent } from './user-components/product-generate-trade-code/product-generate-trade-code.component';
import { TicketDescontGenerateComponent } from './admin-components/tickets/ticket-descont-generate/ticket-descont-generate.component';
import { ClientRequestActivationComponent } from './admin-components/clients/client-request-activation/client-request-activation.component';
import { PendingOrdersComponent } from './admin-components/orders/pending-orders/pending-orders.component';
import { TransitOrdersComponent } from './admin-components/orders/transit-orders/transit-orders.component';
import { OrdersDeliveredComponent } from './admin-components/orders/orders-delivered/orders-delivered.component';
import { TicketValueComponent } from './admin-components/tickets/ticket-value/ticket-value.component';
import { StockInsertComponent } from './admin-components/stock/stock-insert/stock-insert.component';
import { RequestTradeComponent } from './admin-components/trade/request-trade/request-trade.component';
import { TransitTradeComponent } from './admin-components/trade/transit-trade/transit-trade.component';
import { AnalyzeComponent } from './admin-components/analyze/analyze.component';
import { ReturnStockComponent } from './admin-components/trade/return-stock/return-stock.component';
import { DevolutionComponent } from './user-components/devolution/devolution.component';
import { DevolutionFinalizationComponent } from './user-components/devolution-finalization/devolution-finalization.component';
import { DevolutionRequestComponent } from './admin-components/devolution/devolution-request/devolution-request.component';
import { DevolutionStockComponent } from './admin-components/devolution/devolution-stock/devolution-stock.component';
import { DevolutionTransitComponent } from './admin-components/devolution/devolution-transit/devolution-transit.component';
import { ProviderRegisterComponent } from './admin-components/provider/provider-register/provider-register.component';
import { ProviderListComponent } from './admin-components/provider/provider-list/provider-list.component';
import { ProviderInativeComponent } from './admin-components/provider/provider-inative/provider-inative.component';
import { TicketListComponent } from './admin-components/tickets/ticket-list/ticket-list.component';
import { TicketDevolutionListComponent } from './admin-components/tickets/ticket-devolution-list/ticket-devolution-list.component';
import { TicketTradeListComponent } from './admin-components/tickets/ticket-trade-list/ticket-trade-list.component';
import {ClienteService} from '../services/cliente.service';
import {HttpClientModule} from '@angular/common/http';
import {Util} from './shared/app.util';
import {UsuarioService} from '../services/usuario.service';
import { AppLogadoComponent } from './app-logado/app-logado.component';
import { SelectCreditCardComponent } from './buy-components/select-credit-card/select-credit-card.component';
import { SelectAddressComponent } from './buy-components/select-address/select-address.component';
import { NgxMaskModule, IConfig } from 'ngx-mask';
import { ClientOrdersComponent } from './admin-components/clients/client-orders/client-orders.component';

export let options: Partial<IConfig> | (() => Partial<IConfig>);

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
    CardActiveComponent,
    ClientListComponent,
    ClientActiveComponent,
    ClientInactiveComponent,
    ClientInactiveListComponent,
    ProductTradeComponent,
    ProductGenerateTradeCodeComponent,
    TicketDescontGenerateComponent,
    ClientRequestActivationComponent,
    PendingOrdersComponent,
    TransitOrdersComponent,
    OrdersDeliveredComponent,
    TicketValueComponent,
    StockInsertComponent,
    RequestTradeComponent,
    TransitTradeComponent,
    AnalyzeComponent,
    ReturnStockComponent,
    DevolutionComponent,
    DevolutionFinalizationComponent,
    DevolutionRequestComponent,
    DevolutionStockComponent,
    DevolutionTransitComponent,
    ProviderRegisterComponent,
    ProviderListComponent,
    ProviderInativeComponent,
    TicketListComponent,
    TicketDevolutionListComponent,
    TicketTradeListComponent,
    AppLogadoComponent,
    SelectCreditCardComponent,
    SelectAddressComponent,
    ClientOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(ROUTES),
    FormsModule,
    NgxMaskModule.forRoot(options),
    HttpClientModule
  ],
  providers: [AuthService, ClienteService, Util, UsuarioService],
  bootstrap: [AppComponent]
})
export class AppModule { }
