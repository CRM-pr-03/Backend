import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { SignupService } from '../signup.service';

@Component({
  selector: 'app-opportunity',
  templateUrl: './opportunity.component.html',
  styleUrls: ['./opportunity.component.css']
})
export class OpportunityComponent implements OnInit {
  fm!: FormGroup;
  opportunities: any[] = []; // Array to hold fetched opportunities
  showOpportunities: boolean = false;
  qualifiedLeads: any[] = []; // Array to hold fetched qualified leads
  showQualifiedLeads: boolean = false; // Variable to toggle visibility of qualified leads

  constructor(
    private fb: FormBuilder,
    private api: SignupService,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.fm = this.fb.group({
      'accountName': ['', Validators.required],
      'category': ['', Validators.required],
      'opportunityName': ['', Validators.required],
      'probability': ['', Validators.required],
      'stage': ['', Validators.required],
      'description': [''],
      'amount': ['', Validators.required],
      'closedDate': ['', Validators.required],
      'forecastCategory': ['', Validators.required],
      // Add other form controls and validators as needed
    });
  }

  createOpportunity() {
    if (this.fm.valid) {
      this.api.createOpportunity(this.fm.value).subscribe({
        next: (response) => {
          console.log(response);
          this.toast.success('Opportunity created successfully');
          // You can redirect to another page or perform other actions upon success
        },
        error: (error) => {
          console.error(error);
          this.toast.error('Failed to create opportunity');
        }
      });
    } else {
      this.toast.error('Please fill in all required fields');
    }
  }

  getOpportunitiesByCategory() {
    const category = this.fm.get('category')?.value;
    if (category) {
      this.api.getOpportunityLabelsByCategory(category).subscribe({
        next: (response: any[]) => {
          this.opportunities = response;
          this.toast.success('Opportunities fetched successfully');
        },
        error: (error) => {
          console.error(error);
          this.toast.error('Failed to fetch opportunities');
        }
      });
    }
  }

  getQualifiedListByCategory() {
    const category = this.fm.get('category')?.value;
    if (category) {
      this.api.getQualifiedLeadNamesByCategory(category).subscribe({
        next: (response: any[]) => {
          this.qualifiedLeads = response;
          this.showQualifiedLeads = true; // Set to true to display qualified leads
          this.toast.success('Qualified leads fetched successfully');
        },
        error: (error) => {
          console.error(error);
          this.toast.error('Failed to fetch qualified leads');
        }
      });
    }
  }

  toggleOpportunityDetails() {
    this.showOpportunities = !this.showOpportunities; // Toggle the visibility of opportunity details
    if (this.showOpportunities) {
      this.getOpportunitiesByCategory(); // Fetch opportunities when the button is clicked and details are shown
    }
  }

  toggleQualifiedLeads() {
    this.showQualifiedLeads = !this.showQualifiedLeads; // Toggle the visibility of qualified leads
    if (this.showQualifiedLeads) {
      this.getQualifiedListByCategory(); // Fetch qualified leads when the button is clicked and details are shown
    }
  }

}
