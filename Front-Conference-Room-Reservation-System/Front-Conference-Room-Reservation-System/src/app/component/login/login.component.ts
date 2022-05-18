import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organization } from 'src/app/model/Organization';
import { LoginService } from 'src/app/service/loginService/login.service';
import { AuthService } from 'src/app/service/authService/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  organization: Organization;
  organizationName: string = '';
  email: string = '';
  password: string = '';
  invalidLogin: boolean;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private authService: AuthService) { }


  ngOnInit(): void {
  }

  createOrganization() {
    let organizationRequest = {
      "organizationName": this.organizationName,
      "email": this.email, "password": this.password
    }
    this.loginService.createOrganization(organizationRequest as Organization).subscribe((data) => console.warn(data));
  }


  goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }

  getOrganization(organizationName: string) {
    this.loginService.getOrganization(organizationName).subscribe(organization => this.organization = organization)
  }

  showName() {
    console.log(this.organizationName)
    console.log(this.password)
  }

  // signIn() {
  //   this.authService.login(this.organizationName, this.password)
  //     .subscribe(result => {
  //       if (result)
  //         this.router.navigate(['/']);
  //       else
  //         this.invalidLogin = true;
  //     });
  // }


}
