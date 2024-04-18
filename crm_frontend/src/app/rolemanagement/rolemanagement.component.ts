import { Component, OnInit } from '@angular/core';
import { SignupService } from '../signup.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-rolemanagement',
  templateUrl: './rolemanagement.component.html',
  styleUrls: ['./rolemanagement.component.css']
})
export class RolemanagementComponent implements OnInit {
  users: any[] = [];
  email: string = '';
  role: string = '';

  constructor(private api: SignupService, private toastr: ToastrService) {}

  ngOnInit(): void {
    this.getUsers();
  }
 
  assignRole() {
    this.api.assignRole(this.email, this.role).subscribe(
      (response: any) => {
        console.log('Role assigned successfully:', response);
        this.toastr.success('Role assigned successfully!', 'Success');
        // Update the user's role in the users array
        const userIndex = this.users.findIndex(user => user.email === this.email);
        if (userIndex !== -1) {
          this.users[userIndex].role = this.role;
        }
      },
      error => {
        console.error('Error assigning role:', error);
        // Handle error response
        this.toastr.error('Error assigning role!', 'Error');
      }
    );
  }

  getUsers(): void {
    this.api.getUsers().subscribe(
      (data: any[]) => {
        this.users = data.map(user => ({
          ...user,
          access: user.access ? 'Granted' : 'Denied'
        }));
      },
      error => {
        console.error('Error fetching users:', error);
        // Handle error response
        this.toastr.error('Error fetching users!', 'Error');
      }
    );
  }
}
