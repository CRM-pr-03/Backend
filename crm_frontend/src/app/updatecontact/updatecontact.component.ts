import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastrService } from 'ngx-toastr';
import { SignupService } from '../signup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updatecontact',
  templateUrl: './updatecontact.component.html',
  styleUrl: './updatecontact.component.css'
})
export class UpdatecontactComponent {

  user: any;
  email: any;
  submitted = false;
  fm!: FormGroup;
 
  constructor(
    private fb: FormBuilder,
    private api: SignupService,

    private _router: Router,
    private toast: ToastrService
  ) {}
 
  ngOnInit(): void {
    this.createForm();
    this.user = this.api.getUserId(); // Retrieve user ID
  }
 
  createForm() {
    this.fm = this.fb.group({
      'id':['',Validators.required],
      'name': ['', Validators.required],
      'category': ['', Validators.required],
      'phoneNumber': ['', [Validators.required, Validators.pattern('^\\d{10}$')]],
      'email': ['', [Validators.required, Validators.email]],
      'country': ['', Validators.required]
    });
  }
 
  addContact(values: any) {
    this.submitted = true;
 
    if (this.fm.valid) {
      console.log(values);
      // Assuming you need to send the user ID along with the contact data
      const userId = this.user; // Retrieve user ID
      this.api.addcontact(values, userId).subscribe({
          next: resp => {
              console.log(resp);
              this.toast.success('Contact updated successfully');
              this._router.navigate(['/user']);
          },
          error: err => {
              console.error(err);
              this.toast.error('Failed to update contact');
          }
      });
    }
  }
}
