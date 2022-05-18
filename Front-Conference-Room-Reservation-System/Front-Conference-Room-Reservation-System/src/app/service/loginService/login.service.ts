import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organization } from 'src/app/model/Organization';
import { environment } from 'src/environments/environment';




@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiServerUrl: string = environment.apiServerUrl + "/organization"
  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  };
  constructor(private httpClient: HttpClient) { }

  createOrganization(organization: Organization): Observable<Organization> {
    localStorage.setItem("organizationName", organization.organizationName);
    localStorage.setItem("email", organization.email);
    localStorage.setItem("password", organization.password);
    return this.httpClient.post<Organization>(this.apiServerUrl, organization, this.httpOptions);
  }

  public addOrganization(organization: Organization): Observable<Organization> {
    return this.httpClient.post<Organization>(`${this.apiServerUrl}`, organization, this.httpOptions);
  }

  getOrganization(organizationName: string): Observable<Organization> {
    return this.httpClient.get<Organization>(this.apiServerUrl + organizationName)

  }
}
