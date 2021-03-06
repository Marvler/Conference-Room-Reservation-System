import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organization } from 'src/app/model/Organization';
import { LoginService } from 'src/app/service/loginService/login.service';
import { AuthService } from 'src/app/service/authService/auth.service';
import { OrganizationService } from 'src/app/service/organizationService/organization.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  organization: Organization;
  organizationId: number;
  organizationName: string;
  email: string;
  password: string;
  invalidLogin: boolean;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private authService: AuthService,
    private organizationService: OrganizationService) { }


  ngOnInit(): void {
  }

  createOrganization() {
    let organizationRequest = {
      "organizationName": this.organizationName,
      "email": this.email,
      "password": this.password
    }
    this.loginService.createOrganization(organizationRequest as Organization).subscribe((data) => console.warn(data));
  }


  goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }

  getOrganization(organizationName: string) {
    this.loginService.getOrganization(organizationName).subscribe(organization => this.organization = organization)
  }

  getOrganizationId(organizationName: string) {
    this.loginService.getOrganizationId(organizationName).subscribe(organization => this.organization = organization)
    setTimeout(() => {
      console.log(this.organization);
      this.organizationId = this.organization.organizationId;
    }, 213.7);
  }

  showName() {
    console.log(this.organizationName)
    console.log(this.password)
  }

  signIn() {
    if (this.organizationName === 'admin' && this.password === 'admin') {
      this.router.navigate(['admin'])
    } else {
      this.getOrganizationId(this.organizationName);
      setTimeout(() => {
        this.router.navigate(['organization', this.organizationId]);
      }, 420);
    }
  }


}
