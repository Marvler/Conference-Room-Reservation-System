import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Organization } from 'src/app/model/Organization';
import { OrganizationService } from 'src/app/service/organizationService/organization.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  public organizations: Organization[];

  constructor(private organizationService: OrganizationService) {
    this.organizations = [];
  }

  ngOnInit(): void {
    this.getOrganizations();
  }

  getOrganizations(): void {
    this.organizationService.getOrganizations().subscribe(
      (response: Organization[]) => {
        this.organizations = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }

    );
  }

}