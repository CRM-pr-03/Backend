import { Component } from '@angular/core';
import { AddserviceService } from '../addservice.service';
import { AddContact } from '../addcontact';


@Component({
  selector: 'app-addcontact',
  templateUrl: './addcontact.component.html',
  styleUrls: ['./addcontact.component.css']
})
export class AddcontactComponent {
  newContact: AddContact = {} as AddContact; // Initialize newContact as an empty object

  constructor(private api: AddserviceService) {}

  onSubmit() {
    this.api.addContact(this.newContact).subscribe(
      response => {
       alert('Contact added successfully:');
        alert("contact added successfully");
        // Optionally reset the form or perform other actions after successful submission
        this.newContact = {} as AddContact; // Reset newContact
      },
      error => {
        alert('Error adding contact:');
      }
    );
  }

}
