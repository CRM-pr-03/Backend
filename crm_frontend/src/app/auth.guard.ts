import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService // Inject ToastrService
  ) {}

  canActivate(): boolean {
    const role = this.authService.getUserRole();

    if (role === 'A') {
      return true; // Allow access to admin routes
    } else if (role === 'U') {
      return true; // Allow access to user routes
    } else {
      // Display toaster message
      this.toastr.warning('Please login to access this page', 'Unauthorized');
      // Redirect to login page
      this.router.navigate(['/login']);
      return false;
    }
  }
}
