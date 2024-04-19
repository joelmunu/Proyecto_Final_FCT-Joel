import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: any = null;

  constructor() { }

  setToken(token: any) {
    if (token && token.token) {
      this.token = token.token;
    } else {
      console.error("Token inválido:", token);
    }
  }

  getToken() {
    return this.token;
  }

  deleteSessionToken() {
    this.token = null;
  }
}
