import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { AuthService } from '../service/auth.service';
import { RegisterData } from '../models/register.interface';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [HttpClientModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent implements OnInit {
  RegisterForm = {
    dni: '',
    nombre: '',
    apellidos: '',
    email: '',
    telefono: '',
    username: '',
    contrasena: ''
  };

  constructor(private api: ApiService, private router: Router, private authService: AuthService) {}

  ngOnInit(): void {}

  Register(form: RegisterData) {
    if (
      this.RegisterForm.dni !== "" ||
      this.RegisterForm.nombre !== "" ||
      this.RegisterForm.apellidos !== "" ||
      this.RegisterForm.email !== "" ||
      this.RegisterForm.telefono !== "" ||
      this.RegisterForm.username !== "" ||
      this.RegisterForm.contrasena !== ""
    ) {
      this.api.Register(form).subscribe((data) => {
        this.authService.setToken(data);
        this.router.navigate(["/home"])
      });
    } else {
      alert('ERROR:\nTodos los campos son requeridos.');
    }
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }
}
