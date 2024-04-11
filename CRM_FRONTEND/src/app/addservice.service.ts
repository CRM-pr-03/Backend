import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddContact } from './addcontact';

@Injectable({
  providedIn: 'root'
})
export class AddserviceService {
  private apiUrl = 'http://localhost:9099/api/contacts/addcontacts'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  addContact(contact: AddContact): Observable<any> {
    return this.http.post(this.apiUrl, contact);
  }
}
