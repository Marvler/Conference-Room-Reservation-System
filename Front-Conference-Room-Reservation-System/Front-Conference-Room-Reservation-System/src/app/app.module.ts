import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { OrganizationComponent } from './component/organization/organization.component';
import { ReservationComponent } from './component/reservation/reservation.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { SuccessComponent } from './component/success/success.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminPageComponent } from './component/admin-page/admin-page.component';
import { FooterComponent } from './component/footer/footer.component';
import { LogoutComponent } from './component/logout/logout.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OrganizationComponent,
    ReservationComponent,
    PageNotFoundComponent,
    SuccessComponent,
    AdminPageComponent,
    FooterComponent,
    LogoutComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
