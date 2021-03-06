import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { OrganizationComponent } from './component/organization/organization.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { ReservationComponent } from './component/reservation/reservation.component';
import { SuccessComponent } from './component/success/success.component';
import { AccessForbiddenComponent } from './component/access-forbidden/access-forbidden.component';
import { AdminPageComponent } from './component/admin-page/admin-page.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'organization/:organizationId', component: OrganizationComponent },
  { path: 'reservations/:conferenceRoomId', component: ReservationComponent },
  { path: 'admin', component: AdminPageComponent },
  { path: 'success', component: SuccessComponent },
  { path: 'forbidden', component: AccessForbiddenComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
