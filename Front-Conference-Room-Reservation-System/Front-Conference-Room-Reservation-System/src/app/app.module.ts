import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { OrganizationComponent } from './component/organization/organization.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OrganizationComponent,
  ],
  imports: [
    BrowserModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
