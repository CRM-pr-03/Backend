import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAdmin() {
    throw new Error('Method not implemented.');
  }

  constructor() { }

  // Simulated user role retrieval method
  getUserRole(): string | null {
    return sessionStorage.getItem('role');
  }

  // Simulated login method for admin
  loginAdmin(username: string, password: string): boolean {
    // Implement your admin login logic here
    // For demonstration purposes, let's assume the login is successful if the username is 'admin' and the password is 'admin'
    if (username === 'admin' && password === 'admin') {
      // Store the role in session storage upon successful login
      sessionStorage.setItem('role', 'A');
      return true;
    } else {
      return false;
    }
  }

  // Simulated login method for user
  loginUser(email: string, password: string): boolean {
    // Implement your user login logic here
    // For demonstration purposes, let's assume the login is successful if the email is 'user@example.com' and the password is 'password'
    if (email === 'user@example.com' && password === 'password') {
      // Store the role in session storage upon successful login
      sessionStorage.setItem('role', 'U');
      return true;
    } else {
      return false;
    }
  }

  // Simulated logout method
  logout(): void {
    // Clear the role from session storage upon logout
    sessionStorage.removeItem('role');
  }

  // Method to check if the user is logged in
  isLoggedIn(): boolean {
    return !!sessionStorage.getItem('role');
  }


}
