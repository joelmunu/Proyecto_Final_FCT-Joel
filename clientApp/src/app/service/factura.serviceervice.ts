import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Factura } from '../models/factura.interface';
import { AuthService } from './auth.service';

const baseUrl = 'http://localhost:8888/facturas';

@Injectable({ providedIn: 'root' })
export class FacturaService {
  constructor(private http: HttpClient, private authService: AuthService) {}

  getFacturas(): Observable<Factura[]> {
    const headers = new HttpHeaders({ 'Authorization': `Bearer ${this.authService.getToken()}` });
    return this.http.get<Factura[]>(baseUrl, { headers });
  }
}