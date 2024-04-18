import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RegisterData } from '../models/register.interface';
import { ResponseData } from '../models/response.interface';
import { LoginData } from '../models/login.interface';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  
  private registerUrl = 'http://localhost:8888/auth/register';
  private loginUrl = 'http://localhost:8888/auth/login';

  constructor(private http: HttpClient) {}

  Register(form: RegisterData): Observable<ResponseData> {
    return this.http.post<ResponseData>(this.registerUrl, form);
  }

  Login (form: LoginData): Observable<ResponseData> {
    return this.http.post<ResponseData>(this.loginUrl, form);
  }
}