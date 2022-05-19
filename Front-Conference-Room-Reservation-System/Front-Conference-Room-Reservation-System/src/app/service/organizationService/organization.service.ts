import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Organization } from 'src/app/model/Organization';
import { catchError, map, tap } from 'rxjs/operators';



@Injectable({ providedIn: 'root' })
export class OrganizationService {
  private apiServerUrl = environment.apiServerUrl + "/organization";
  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  };
  orgarnization: Organization;

  constructor(private http: HttpClient) { }

  public getOrganizations(): Observable<Organization[]> {
    return this.http.get<Organization[]>(`${this.apiServerUrl}/all`, this.httpOptions);
  }

  public getOrganization(organizationId: number): Observable<Organization> {
    return this.http.get<Organization>(`${this.apiServerUrl}/${organizationId}`, this.httpOptions);
  }

  public addOrganization(organization: Organization): Observable<Organization> {
    return this.http.post<Organization>(`${this.apiServerUrl}/`, organization, this.httpOptions);
  }

  public updateOrganization(organization: Organization): Observable<Organization> {
    return this.http.put<Organization>(`${this.apiServerUrl}/${organization.organizationId}`, organization, this.httpOptions);
  }

  public deleteOrganization(organizationId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${organizationId}`, this.httpOptions);
  }

  // public getOrganizationByName(organizationName: string) {
  //   this.getOrganizationJson(organizationName)
  //   .subscribe((data:Organization) => this.orgarnization = {
  //     organizationId: this.data.organizationId,
  //     organizationName: this.data.organizationName,
  //     password: this.data.password,
  //     email: this.data.email,
  //     conferenceRooms: this.data.conferenceRooms
  //   })
  // }

  // private getOrganizationJson(organizationName: string){
  //   return this.http.get<Organization>(`${this.apiServerUrl}/organization/auth${organizationName}`, this.httpOptions)
  // }




}

