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
  
}
  
