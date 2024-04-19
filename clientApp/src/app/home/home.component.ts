import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { FacturaService } from '../service/factura.serviceervice';

@Component({
  selector: 'app-home-screen',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private router: Router, private authService: AuthService, private facturaService: FacturaService) {}

  logOut() {
    this.router.navigate(['/login']);
    this.authService.deleteSessionToken();
  }

  facturas() {
    console.log(this.facturaService.getFacturas());
  }
}