import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SignupService } from '../signup.service';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  submitted = false;

  fg!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private api: SignupService,
    private _router: Router,
    private toast: ToastrService
  ) {
    this.createForm();
  }
  ngOnInit(): void {}

  createForm() {
    this.fg = this.fb.group({
      'email': ['', [Validators.required, Validators.email]],
      'password': ['', [Validators.required, Validators.minLength(8), this.customPasswordValidator()]],
    });
  
  
  }
  customPasswordValidator(): any | string {
    return (control: any) => {
      const password = control.value;
      if (!password) {
        return null;
      }
      const hasUppercase = /[A-Z]/.test(password);
      const hasLowercase = /[a-z]/.test(password);
      const hasNumber = /\d/.test(password);
      const isValid = hasUppercase && hasLowercase && hasNumber;
      return isValid ? null : { invalidPassword: true };
    };

    
  }

  validate(values: any) {
    this.submitted = true;
  
    if (this.fg.valid) {
      console.log(values);
  
      this.api.validate(values).subscribe(
        (resp: any) => {
          console.log(resp);
  
          if (resp.status === 'logged in') {
            // Handle successful login
          
            sessionStorage.setItem("id", resp.data.id);
            sessionStorage.setItem("email", resp.data.email);
            sessionStorage.setItem("firstname", resp.data.firstname);
            sessionStorage.setItem("role","U")
           
         
            this.toast.success('Welcome '+resp.data.firstname,"Login Successful" );
            this._router.navigate(['user']);
          } else {
            console.error('Invalid response format:', resp);
            
            this.toast.error('Unexpected response format', 'Login Failed',{ timeOut: 2000 });
          }
        },
        (err) => {
          console.error('HTTP error:', err);
         
          this.toast.error('Invalid userid or password', 'Login Failed');
        }
      );
    }
  }
  
    }

 
