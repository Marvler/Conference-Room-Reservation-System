import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(private route: Router) { }

  public logout() {
    this.route.navigate(['login'])
  }
}
