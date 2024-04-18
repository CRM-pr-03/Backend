import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { SignupService } from '../signup.service';
import { Router } from '@angular/router';
import {  ChangeDetectorRef } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
 
@Component({
  selector: 'app-onboard',
  templateUrl: './onboard.component.html',
  styleUrls: ['./onboard.component.css'], // Note the correct property name 'styleUrls'
  // changeDetection: ChangeDetectionStrategy.OnPush
})
export class OnboardComponent implements OnInit {
  users!: any[];
 
  constructor(private api: SignupService, private _router:Router,private cdr: ChangeDetectorRef, private toast: ToastrService) {}
 
 
  ngOnInit(): void {
    this.getUsers();
  }
 
  getUsers(): void {
    this.api.getUsers().subscribe(data => {
      this.users = data.map((user: any) => ({
        ...user,
        access: user.access ? 'Granted' : 'Denied',
       
     
      }));
    });
  }
 
  approveAccess(email: string): void {
    const user = this.users.find(u => u.email === email);
    if (user) {
      // Toggle the access status
      user.access = user.access === 'Denied' ? 'Granted' : 'Denied';
 
      // Call the API to update access status
      this.api.approveAccess(email).subscribe(response => {
        // Handle response if needed
        console.log(response);
        // You can choose to update the users after successful approval
        this.getUsers();
      });
    }
    (err: any) => {
      console.error('HTTP error:', err);
      this.toast.error('server is down', 'unable to give access');
    }
  }
}