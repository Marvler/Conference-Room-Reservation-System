import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Organization } from 'src/app/model/Organization';
import { LogoutService } from 'src/app/service/logutService/logout.service';
import { OrganizationService } from 'src/app/service/organizationService/organization.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  public organizations: Organization[];
  public editOrganization: Organization;
  public deleteOrganization: Organization;

  constructor(
    private organizationService: OrganizationService,
    private router: Router,
    private logoutService: LogoutService) { }

  ngOnInit() {
    this.getOrganizations();
    console.log(this.organizations)
  }

  public getOrganizations(): void {
    this.organizations = [];
    this.organizationService.getOrganizations().subscribe(
      (response: Organization[]) => {
        response.forEach(element => {
          if (element.organizationName !== 'admin') { this.organizations.push(element) }
        });
        console.log(this.organizations);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddOrganization(addForm: NgForm): void {
    document.getElementById('add-organization-form')!.click();
    this.organizationService.addOrganization(addForm.value).subscribe(
      (response: Organization) => {
        console.log(response);
        this.getOrganizations();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateOrganization(organization: Organization): void {
    this.organizationService.updateOrganization(organization).subscribe(
      (response: Organization) => {
        console.log(response);
        this.getOrganizations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteOrganization(organizationId: number): void {
    this.organizationService.deleteOrganization(organizationId).subscribe(
      (response: void) => {
        console.log(response);
        this.getOrganizations();
      },
      (error: HttpErrorResponse) => {
        alert("Ooops, Something went wrong");
      }
    );
  }

  public searchOrganizations(key: string): void {
    console.log(key);
    const results: Organization[] = [];
    for (const organization of this.organizations) {
      if (organization.organizationName.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(organization);
      }
    }
    this.organizations = results;
    if (key.length === 0) {
      this.getOrganizations();
    }
  }

  public onOpenModal(organization: Organization, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addOrganizationModal');
    }
    if (mode === 'edit') {
      this.editOrganization = organization;
      button.setAttribute('data-target', '#updateOrganizationModal');
    }
    if (mode === 'delete') {
      this.deleteOrganization = organization;
      button.setAttribute('data-target', '#deleteOrganizationModal');
    }
    if (mode === 'room') {
      this.router.navigate(['organization', organization.organizationId])
    }
    container!.appendChild(button);
    button.click();
  }

  public logout() {
    this.logoutService.logout();
  }

}
