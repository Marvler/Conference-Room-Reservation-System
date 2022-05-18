import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { OrganizationService } from '../organizationService/organization.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiBaseUrl: string = environment.apiServerUrl;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  }


  constructor(private http: HttpClientModule, private organizationService: OrganizationService) { }



  // login(organizationName: string, password: string) {
  //   this.organizationService.getOrganizationByName(organizationName);
  // }

  logout() {
  }

  isLoggedIn() {
    return false;
  }
}
