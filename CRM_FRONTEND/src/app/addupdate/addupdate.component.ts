import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-addupdate',
  templateUrl: './addupdate.component.html',
  styleUrls: ['./addupdate.component.css']
})
export class AddupdateComponent implements OnInit {
  contactForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar
  ) { 
    this.contactForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      country: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      category: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.contactForm.valid) {
      this.saveContact();
    } else {
      this.snackBar.open('Please fill all required fields', 'Close', {
        duration: 3000,
      });
    }
  }

  saveContact() {
    this.httpClient.post('http://localhost:9099/api/contacts/addcontacts', this.contactForm.value)
      .subscribe((response) => {
        if (response) {
          this.snackBar.open('Contact saved successfully', 'Close', {
            duration: 3000,
          });
          this.contactForm.reset();
        } else {
          this.snackBar.open('Failed to save contact', 'Close', {
            duration: 3000,
          });
        }
      }, (error) => {
        console.error('Error:', error);
        this.snackBar.open('Failed to save contact', 'Close', {
          duration: 3000,
        });
      });
  }
}
