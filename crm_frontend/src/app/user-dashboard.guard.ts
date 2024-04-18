// user-dashboard.guard.ts
import { Injectable } from '@angular/core';
import { CanActivateChild, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserDashboardGuard implements CanActivateChild {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivateChild(): boolean {
    if (this.authService.getUserRole() === 'U') {
      return true; // Allow access for users
    } else {
      this.router.navigate(['/login']); // Redirect to login page for non-users
      return false;
    }
  }
}
