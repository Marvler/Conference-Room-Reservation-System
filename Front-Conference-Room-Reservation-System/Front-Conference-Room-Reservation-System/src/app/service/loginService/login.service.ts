import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organization } from 'src/app/model/Organization';




@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private organizationUrl: string = "http://localhost:8081/api/organization/"

  constructor(private httpClient: HttpClient) { }

  createOrganization(organization: Organization): Observable<Organization> {
    localStorage.setItem("organizationName", organization.organizationName);
    localStorage.setItem("email", organization.email);
    localStorage.setItem("password", organization.password);
    return this.httpClient.post<Organization>(this.organizationUrl + "add", organization);
  }

  getOrganization(organizationName: string): Observable<Organization> {
    return this.httpClient.get<Organization>(this.organizationUrl + organizationName)

  }
}
