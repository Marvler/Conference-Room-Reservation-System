import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Organization } from 'src/app/model/Organization';

@Injectable({ providedIn: 'root' })
export class OrganizationService {
  private apiServerUrl = environment.apiServerUrl;
  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  };

  constructor(private http: HttpClient) { }

  public getOrganizations(): Observable<Organization[]> {
    return this.http.get<Organization[]>(`${this.apiServerUrl}/organization/all`, this.httpOptions);
  }

  public getOrganization(organizationId: number): Observable<Organization> {
    return this.http.get<Organization>(`${this.apiServerUrl}/organization/${organizationId}`, this.httpOptions);
  }

  public addOrganization(organization: Organization): Observable<Organization> {
    return this.http.post<Organization>(`${this.apiServerUrl}/organization/`, organization, this.httpOptions);
  }

  public updateOrganization(organization: Organization): Observable<Organization> {
    return this.http.put<Organization>(`${this.apiServerUrl}/organization/${organization.organizationId}`, organization, this.httpOptions);
  }

  public deleteOrganization(organizationId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/organization/${organizationId}`, this.httpOptions);
  }
}

