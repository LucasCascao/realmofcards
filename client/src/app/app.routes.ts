/* eslint-disable no-unused-vars */
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
import {CreditcardRegisterComponent} from './buy-components/creditcard/creditcard-register/creditcard-register.component';
import {CreditcardDeleteComponent} from './buy-components/creditcard/creditcard-delete/creditcard-delete.component';
import {CreditcardListComponent} from './buy-components/creditcard/creditcard-list/creditcard-list.component';
import {UserPasswordAlterComponent} from './user-components/user-password-alter/user-password-alter.component';
import {CartComponent} from './buy-components/cart/cart.component';
import {OrderResumeComponent} from './buy-components/order-resume/order-resume.component';
import {PaymentPageComponent} from './buy-components/payment-page/payment-page.component';
import {BuyConfirmComponent} from './buy-components/buy-confirm/buy-confirm.component';
import {CardRegisterComponent} from './admin-components/cards/card-register/card-register.component';
import {CardAlterComponent} from './admin-components/cards/card-alter/card-alter.component';
import {CardDeleteComponent} from './admin-components/cards/card-delete/card-delete.component';
import {CardListComponent} from './admin-components/cards/card-list/card-list.component';
import {AdminPageComponent} from './admin-components/admin-page/admin-page.component';
import {CardListInactiveComponent} from './admin-components/cards/card-list-inactive/card-list-inactive.component';
import {CardActiveComponent} from './admin-components/cards/card-active/card-active.component';
import {ProductTradeComponent} from './user-components/product-trade/product-trade.component';
import {ProductGenerateTradeCodeComponent} from './user-components/product-generate-trade-code/product-generate-trade-code.component';
import {ClientListComponent} from './admin-components/clients/client-list/client-list.component';
import {ClientInactiveListComponent} from './admin-components/clients/client-inactive-list/client-inactive-list.component';
import {ClientInactiveComponent} from './admin-components/clients/client-inactive/client-inactive.component';
import {ClientActiveComponent} from './admin-components/clients/client-active/client-active.component';
import {ClientRequestActivationComponent} from './admin-components/clients/client-request-activation/client-request-activation.component';
import {PendingOrdersComponent} from './admin-components/orders/pending-orders/pending-orders.component';
import {TransitOrdersComponent} from './admin-components/orders/transit-orders/transit-orders.component';
import {OrdersDeliveredComponent} from './admin-components/orders/orders-delivered/orders-delivered.component';
import {TicketDescontGenerateComponent} from './admin-components/tickets/ticket-descont-generate/ticket-descont-generate.component';
import {TicketValueComponent} from './admin-components/tickets/ticket-value/ticket-value.component';
import {StockInsertComponent} from './admin-components/cards/stock/stock-insert/stock-insert.component';
import {RequestTradeComponent} from './admin-components/trade/request-trade/request-trade.component';
import {TransitTradeComponent} from './admin-components/trade/transit-trade/transit-trade.component';
import {AnalyzeComponent} from './admin-components/analyze/analyze.component';
import {ReturnStockComponent} from './admin-components/trade/return-stock/return-stock.component';
import {DevolutionComponent} from './user-components/devolution/devolution.component';
import {DevolutionFinalizationComponent} from './user-components/devolution-finalization/devolution-finalization.component';
import {DevolutionRequestComponent} from './admin-components/devolution/devolution-request/devolution-request.component';
import {DevolutionTransitComponent} from './admin-components/devolution/devolution-transit/devolution-transit.component';
import {DevolutionStockComponent} from './admin-components/devolution/devolution-stock/devolution-stock.component';
import {ProviderRegisterComponent} from './admin-components/provider/provider-register/provider-register.component';
import {ProviderInativeComponent} from './admin-components/provider/provider-inative/provider-inative.component';
import {ProviderListComponent} from './admin-components/provider/provider-list/provider-list.component';
import {TicketDevolutionListComponent} from './admin-components/tickets/ticket-devolution-list/ticket-devolution-list.component';
import {TicketTradeListComponent} from './admin-components/tickets/ticket-trade-list/ticket-trade-list.component';
import {AppLogadoComponent} from './app-logado/app-logado.component';
import {SelectCreditCardComponent} from './buy-components/select-credit-card/select-credit-card.component';
import {SelectAddressComponent} from './buy-components/select-address/select-address.component';
import { ClientOrdersComponent } from './admin-components/clients/client-orders/client-orders.component';
import { UserTicketsComponent } from './user-components/user-tickets/user-tickets.component';
import { TicketListComponent } from './admin-components/tickets/ticket-list/ticket-list.component';

export const ROUTES: Routes = [
  {path: '', component: LoginComponent},
  {path: 'app-logado', component: AppLogadoComponent, children: [
      {path: '', redirectTo: 'product-market-page', pathMatch: 'full'},
      {path: 'product-market-page', component: ProductMarketPageComponent},
      {path: 'product-detail', component: ProductDetailComponent},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'user-alter', component: UserAlterComponent},
      {path: 'user-delete', component: UserDeleteComponent},
      {path: 'user-orders', component: UserOrdersComponent},
      {path: 'user-password-alter', component: UserPasswordAlterComponent},
      {path: 'user-product-trade', component: ProductTradeComponent},
      {path: 'user-product-generate-trade-code', component: ProductGenerateTradeCodeComponent},
      {path: 'user-devolution', component: DevolutionComponent},
      {path: 'user-devolution-finalization', component: DevolutionFinalizationComponent},
      {path: 'user-tickets', component: UserTicketsComponent},
      {path: 'address-register', component: AddressRegisterComponent},
      {path: 'address-alter', component: AddressAlterComponent},
      {path: 'address-delete', component: AddressDeleteComponent},
      {path: 'address-list', component: AddressListComponent},
      {path: 'creditcard-register', component: CreditcardRegisterComponent},
      {path: 'creditcard-delete', component: CreditcardDeleteComponent},
      {path: 'creditcard-list', component: CreditcardListComponent},
      {path: 'select-creditcard', component: SelectCreditCardComponent},
      {path: 'select-address', component: SelectAddressComponent},
      {path: 'cart', component: CartComponent},
      {path: 'payment-page', component: PaymentPageComponent},
      {path: 'order-resume', component: OrderResumeComponent},
      {path: 'buy-confirm', component: BuyConfirmComponent},
      {path: 'admin-page', component: AdminPageComponent, children: [
        {path: '', redirectTo: 'admin-product-list', pathMatch: 'full'},
        {path: 'admin-product-register', component: CardRegisterComponent},
        {path: 'admin-product-alter', component: CardAlterComponent},
        {path: 'admin-product-delete', component: CardDeleteComponent},
        {path: 'admin-product-list', component: CardListComponent},
        {path: 'admin-product-inactive-list', component: CardListInactiveComponent},
        {path: 'admin-product-active', component: CardActiveComponent},
        {path: 'stock-insert', component: StockInsertComponent},
        {path: 'admin-client-list', component: ClientListComponent},
        {path: 'admin-client-inactive-list', component: ClientInactiveListComponent},
        {path: 'admin-client-inactive', component: ClientInactiveComponent},
        {path: 'admin-client-request-activation', component: ClientRequestActivationComponent},
        {path: 'admin-client-active', component: ClientActiveComponent},
        {path: 'admin-ticket-value', component: TicketValueComponent},
        {path: 'admin-generate-discount-ticket', component: TicketDescontGenerateComponent},
        {path: 'pending-orders', component: PendingOrdersComponent},
        {path: 'transit-orders', component: TransitOrdersComponent},
        {path: 'orders-delivered', component: OrdersDeliveredComponent},
        {path: 'request-trade', component: RequestTradeComponent},
        {path: 'transit-trade', component: TransitTradeComponent},
        {path: 'return-stock', component: ReturnStockComponent},
        {path: 'devolution-request', component: DevolutionRequestComponent},
        {path: 'devolution-transit', component: DevolutionTransitComponent},
        {path: 'devolution-stock', component: DevolutionStockComponent},
        {path: 'return-stock', component: ReturnStockComponent},
        {path: 'analyze', component: AnalyzeComponent},
        {path: 'provider-register', component: ProviderRegisterComponent},
        {path: 'provider-delete', component: ProviderInativeComponent},
        {path: 'provider-list', component: ProviderListComponent},
        {path: 'ticket-devolution-list', component: TicketDevolutionListComponent},
        {path: 'ticket-list', component: TicketListComponent},
        {path: 'ticket-trade-list', component: TicketTradeListComponent},
        {path: 'client-orders', component: ClientOrdersComponent}
      ]},
    ]},
  {path: 'user-register', component: UserRegisterComponent},
];
