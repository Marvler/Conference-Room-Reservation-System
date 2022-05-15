import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organization } from 'src/app/model/Organization';
import { LoginService } from 'src/app/service/loginService/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) { }


  ngOnInit(): void {
  }

  createOrganization(organizationName: string, email: string, password: string) {
    this.loginService.createOrganization({ organizationName, email, password } as Organization).subscribe((data) => console.warn(data));
  }


  goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }

}
