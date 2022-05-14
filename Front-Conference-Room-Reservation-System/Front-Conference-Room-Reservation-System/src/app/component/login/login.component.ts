import { Component, OnInit } from '@angular/core';
import { Organization } from 'src/app/model/Organization';
import { LoginService } from 'src/app/service/loginService/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  createOrganization(organizationName: string, email: string, password: string) {
    this.loginService.createOrganization({ organizationName, email, password } as Organization).subscribe((data) => console.warn(data));
  }

}
