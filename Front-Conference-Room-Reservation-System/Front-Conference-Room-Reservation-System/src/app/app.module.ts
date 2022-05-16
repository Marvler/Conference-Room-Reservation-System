import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { OrganizationComponent } from './component/organization/organization.component';
import { ReservationComponent } from './component/reservation/reservation.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './component/navbar/navbar.component';
import { SuccessComponent } from './component/success/success.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TestComponent } from './component/test/test.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OrganizationComponent,
    ReservationComponent,
    PageNotFoundComponent,
    NavbarComponent,
    SuccessComponent,
    TestComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
