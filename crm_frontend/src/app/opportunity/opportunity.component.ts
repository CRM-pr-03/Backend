import { Component } from '@angular/core';

@Component({
  selector: 'app-opportunity',
  templateUrl: './opportunity.component.html',
  styleUrls: ['./opportunity.component.css']
})
export class OpportunityComponent {
  accountName: string;
  category: string;
  forecastCategory: string;
  opportunityOwner: string;
  opportunityLabel: string;
  description: string;
  amount: number | null; // Use union type to allow null
  closedDate: Date;
  stage: string;

  constructor() {
    // Initialize your properties if needed
    this.accountName = '';
    this.category = '';
    this.forecastCategory = '';
    this.opportunityOwner = '';
    this.opportunityLabel = '';
    this.description = '';
    this.amount = null; // Initialize with null
    this.closedDate = new Date();
    this.stage = '';
  }
  submit()
  {

  }
}
