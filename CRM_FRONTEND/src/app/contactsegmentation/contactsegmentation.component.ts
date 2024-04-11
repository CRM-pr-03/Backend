import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
interface Contact {
  id: number;
  name: string;
  email: string;
  category: string;
  phoneNumber: string;
  country: string;
  dateCreated: string;
}
@Component({
  selector: 'app-contact-segmentation',
  templateUrl: './contactsegmentation.component.html',
  styleUrls: ['./contactsegmentation.component.css']
})
export class ContactsegmentationComponent {
  searchBy: string = ''; // Define searchBy property
  category: string = '';
  country: string = '';
  contacts: Contact[] = [];
  constructor(private http: HttpClient) {}
  searchByCategory() {
    if (this.category.trim() !== '') {
      this.http.get<Contact[]>(`
http://localhost:9099/api/contacts/segmented/category/${this.category}`)
        .subscribe(contacts => {
          this.contacts = contacts;
        });
    }
  }
  searchByCountry() {
    if (this.country.trim() !== '') {
      this.http.get<Contact[]>(`
http://localhost:9099/api/contacts/segmented/country/${this.country}`)
        .subscribe(contacts => {
          this.contacts = contacts;
        });
    }
  }
}