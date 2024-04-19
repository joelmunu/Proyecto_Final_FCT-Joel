import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { AuthService } from '../service/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoginData } from '../models/login.interface';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [HttpClientModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  loginForm = {
    username: '',
    contrasena: '',
  };

  constructor(
    private api: ApiService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  Login(form: LoginData) {
    if (this.loginForm.username !== '' || this.loginForm.contrasena !== '') {
      this.api.Login(form).subscribe((data) => {
        this.authService.setToken(data);
        this.router.navigate(['/home']);
      });
    } else {
      alert('ERROR:\nTodos los campos son requeridos.');
    }
  }

  navigateToRegister() {
    this.router.navigate(['/register']);
  }
}
