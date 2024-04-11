import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 
 
@Component({
  selector: 'app-leadtracking',
  templateUrl: './leadtracking.component.html',
  styleUrls: ['./leadtracking.component.css']
})
export class LeadtrackingComponent {
  category: string = '';
  status: string = '';
  leadsId: number = 0; // Initialize contactId
  leadTrackings: any[] = []; // Initialize leadTrackings
  // Initialize updateContactId
  newStatus: string = '';
 
  salesReps: any[] = []; // Initialize salesReps
  contactCategory: string = '';
  contacts: any[] = []; // Initialize contacts
  allLeadTrackings: any[] = []; // Initialize allLeadTrackings
 
  constructor(private http: HttpClient) {}
 
  segmentAndAssign() {
    const requestData = {
      category: this.category,
      status: this.status
    };
 
    this.http.post<any>('http://localhost:9099/api/leadtracking/segmentAndAssign', requestData).subscribe(
      response => {
       alert(" Assigned succesfully");
      },
      error => {
        alert("Alredy Assigned");
      }
    );
  }
 
  getLeadTrackingsByContactId() {
    this.http.get<any>(`http://localhost:9099/api/leadtracking/lead-trackings/contact/${this.leadsId}`).subscribe(
      response => {
        this.leadTrackings = response;
      },
      error => {
        alert("Not found");
      }
    );
  }
 
  updateLeadTrackingStatus() {
    const requestData = {
      status: this.newStatus
    };
 
    this.http.put<any>(`http://localhost:9099/api/leadtracking/updateStatus/${this.leadsId}`, requestData).subscribe(
      response => {
        alert("Status updated:");
      },
      error => {
        alert("Not found");
      }
    );
  }
 
 
  getSalesRepresentativesByCategory() {
    this.http.get<any>(`http://localhost:9099/api/leadtracking/sales-representatives/category/${this.category}`).subscribe(
      response => {
        this.salesReps = response;
      },
      error => {
        alert("Not found");
      }
    );
  }
 
  getContactsByCategory() {
    this.http.get<any>(`http://localhost:9099/api/leadtracking/contacts/category/${this.category}`).subscribe(
      response => {
        this.contacts = response;
      },
      error => {
        alert("Not found");
      }
    );
  }
 
  getAllLeadTrackings() {
    this.http.get<any>('http://localhost:9099/api/leadtracking/lead-trackings').subscribe(
      response => {
        this.allLeadTrackings = response;
      },
      error => {
        alert("Not found");
      }
    );
  }
}