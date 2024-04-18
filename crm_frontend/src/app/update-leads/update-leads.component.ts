import { Component, OnInit } from '@angular/core';
import { SignupService } from '../signup.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-leads',
  templateUrl: './update-leads.component.html',
  styleUrl: './update-leads.component.css'
})
export class UpdateLeadsComponent implements OnInit{

  category: string = '';
  status: string = '';
  leadsId: number = 0;
  newStatus: string = '';
  salesReps: any[] = [];
  contacts: any[] = [];
  leadTrackings: any[] = [];


  userId!: number;
  allLeadTrackings: any[] = [];

  constructor(private api: SignupService,  private _router:Router,private toast:ToastrService) {}
  ngOnInit(): void {
    const userIdString = sessionStorage.getItem('id');
    if (userIdString) {
      const parsedUserId = parseInt(userIdString, 10);
      if (!isNaN(parsedUserId)) {
        this.userId = parsedUserId;
      } else {
        console.error('Invalid user ID in session storage');
      }
    } else {
      console.error('User ID not found in session storage');
    }
  }
 

  

  getLeadTrackingsByContactId() {
    this.api.getLeadTrackingsByContactId(this.leadsId).subscribe(
      response => {
        this.leadTrackings = response;
      },
      error => {
        this.toast.error('NOT FOUND',"Please Retry!")
      }
    );
  }

  updateLeadTrackingStatus() {
    const requestData = {
      status: this.newStatus
    };
    this.api.updateLeadTrackingStatus(this.leadsId, requestData).subscribe(
      response => {
        this.toast.success('Successfull',"Status Updated")
      },
      error => {
      
     
         this.toast.error('Invalid',"DATA NOT FOUND")
      }
    );
  }

  getSalesRepresentativesByCategory() {
    this.api.getSalesRepresentativesByCategory(this.category).subscribe(
      response => {
        this.salesReps = response;
      },
      error => {
        this.toast.error('Invalid',"DATA NOT FOUND")
      }
    );
   
  }

  getContactsByCategory() {
    if (this.userId !== undefined) {
      this.api.getContactsByCategory(this.userId, this.category).subscribe(
        response => {
          this.contacts = response;
        },
        error => {
          this.toast.error('CANT FIND', "NO CONTACT FOUND")
        }
      );
    } else {
      console.error('User ID is undefined');
    }
  
  }

  getAllLeadTrackingsByUserIdAndCategory(userId: number, category: string): void {
    if (userId !== undefined && category.trim() !== '') {
      this.api.getAllLeadTrackingsByUserIdAndCategory(userId, category).subscribe(
        (response: any[]) => {
          this.allLeadTrackings = response;
        },
        (error: any) => {
          console.error('Error fetching all lead trackings:', error);
          this.toast.error('Error fetching all lead trackings');
        }
      );
    } else {
      this.toast.warning('Please enter both a user ID and a category');
    }
  }
  
 
}
