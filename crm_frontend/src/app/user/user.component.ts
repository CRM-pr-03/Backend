import { Component, NgZone, OnInit } from '@angular/core';
import { SignupService } from '../signup.service';
import { ToastrService } from 'ngx-toastr';

declare global {
  interface Window {
    fwSettings: {
      widget_id: string;
    };
  }
}

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  searchType: string = "";
  searchValue: string = "";
  fromDate: Date | undefined;
  toDate: Date | undefined;
  Contacts: any[] = [];
  userId: number | undefined;
  currentPage: number = 1;
  pageSize: number = 5;
  totalRecords: number = 0;
  selectedFormat: string = 'csv';
  fetchingData: boolean = false;
  fetchedData: boolean = false;
  countries: string[] = ["INDIA", "USA", "UK", "UAE", "AUSTRALIA", "CHINA", "RUSSIA"];
  categories: string[] = ["Textile", "Retail", "Software", "Pharma", "Automobile", "Construction", "Electronics", "Healthcare", "Food and Beverage", "Finance"];
  selectedCountry: string = ""; // Variable to hold the selected country
  selectedCategory: string = ""; // Variable to hold the selected category

  constructor(private ngZone: NgZone, private api: SignupService, private toast: ToastrService) {
    this.ngZone.runOutsideAngular(() => {
      window.fwSettings = {
        'widget_id': '1070000001175'
      };
      const script = document.createElement('script');
      script.type = 'text/javascript';
      script.async = true;
      script.src = 'https://ind-widget.freshworks.com/widgets/1070000001175.js';
      document.body.appendChild(script);
    });
  }

  ngOnInit(): void {
    const userIdFromStorage = sessionStorage.getItem('id');
    if (userIdFromStorage) {
      this.userId = +userIdFromStorage;
    } else {
      console.error('User ID not found in session storage');
    }
  }

  searchContacts(): void {
    // Retrieve userId from session storage
    const userId = sessionStorage.getItem('id');
    
    if (!userId) {
      console.error('User ID not found in session storage');
      return;
    }
    
    if (this.searchType === 'date') {
      // Handle search by date logic here
    } else {
      if (this.searchType && 
          ((this.searchType === 'country' && this.selectedCountry) || 
          (this.searchType === 'category' && this.selectedCategory) || 
          (this.searchType !== 'country' && this.searchType !== 'category' && this.searchValue))) {
  
        const searchValue = (this.searchType === 'country') ? this.selectedCountry : 
                           (this.searchType === 'category') ? this.selectedCategory : 
                           this.searchValue;
  
        this.api.segmentContacts(parseInt(userId), this.searchType, searchValue)
          .subscribe((contacts: any[]) => {
            this.Contacts = contacts;
          });
  
      } else {
        console.error('Invalid search parameters');
      }
    }
  }
  
}
