import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  }


  constructor(private http: HttpClient) { }



  // login(organizationName: string, password: string) {
  //   credentials = {
  //     "organizationName": organizationName,
  //     "password": password
  //   }
  //   return this.http.post('/api/authenticate',
  //     JSON.stringify(credentials));
  // }

  logout() {
  }

  isLoggedIn() {
    return false;
  }
}
