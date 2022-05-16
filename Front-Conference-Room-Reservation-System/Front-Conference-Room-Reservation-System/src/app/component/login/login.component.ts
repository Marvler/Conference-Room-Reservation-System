import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organization } from 'src/app/model/Organization';
import { LoginService } from 'src/app/service/loginService/login.service';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  organization!: Organization;
  organizationName: string = '';
  email: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) { }


  ngOnInit(): void {
  }

  createOrganization() {
    this.organization = new Organization(this.organizationName, this.email, this.password);
    console.log(this.organizationName, this.email, this.password)
    this.loginService.createOrganization(this.organization as Organization).subscribe((data) => console.warn(data));
  }


  goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }

  getOrganization(organizationName: string) {
    this.loginService.getOrganization(organizationName).subscribe(organization => this.organization = organization)
  }

  showName() {
    console.log(this.organizationName)
  }


}
