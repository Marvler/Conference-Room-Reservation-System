import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Organization } from 'src/app/model/Organization';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  private apiServerUrl = '';

  constructor(private http: HttpClient) {
  }

  public getOrganizations(): Observable<Organization[]> {
    return this.http.get<Organization[]>(`${this.apiServerUrl}/organization/all`)
  }

  public addOrganization(organization: Organization): Observable<Organization> {
    return this.http.post<Organization>(`${this.apiServerUrl}/organization/add`, organization)
  }

  public udpateOrganization(organization: Organization): Observable<Organization> {
    return this.http.put<Organization>(`${this.apiServerUrl}/organization/udpate`, organization)
  }

  public deleteOrganization(organizationName: string): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/organization/delete/${organizationName}`)
  }
}

