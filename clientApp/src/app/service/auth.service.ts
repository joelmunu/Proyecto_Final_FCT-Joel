import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: any = null;

  constructor() { }

  setToken(token: any) {
    this.token = token;
  }

  getToken() {
    return this.token;
  }

  deleteSessionToken() {
    this.token = null;
  }
}
