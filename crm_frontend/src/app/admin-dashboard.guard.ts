// admin-dashboard.guard.ts
import { Injectable } from '@angular/core';
import { CanActivateChild, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminDashboardGuard implements CanActivateChild {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivateChild(): boolean {
    if (this.authService.getUserRole() === 'A') {
      return true; // Allow access for admins
    } else {
      this.router.navigate(['/admin-login']); // Redirect to login page for non-admins
      return false;
    }
  }
}
