import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignupService {
 
 
 
  
  
  BASEURL:string="http://localhost:8080/user/";
  ADMINURL:string="http://localhost:8082/admin/";
  LEADURL:string="http://localhost:8080/leads/";
    constructor(private http:HttpClient)
     {
   
     }

     validateadmin(data:any){
       return this.http.post<any>(this.ADMINURL+"adminlogin",data);
    }

     register(data:any){
      return this.http.post<any>(this.BASEURL+"saveuser",data);
    }
    validate(data:any){
      return this.http.post<any>(this.BASEURL+"login",data);
    }
    reset(email: string, password: string){
      return this.http.put<any>(`${this.BASEURL}forgotpassword/${email}/${password}`, {});
  }
  forgotPassword(email:string): Observable<any>{
    return this.http.post(`${this.BASEURL}forgot-password`,{email},{ responseType: 'text' });
  }
  
  validateOTP(email: string, otp: string): Observable<any> {
    return this.http.post(`${this.BASEURL}validate-otp`,{email,otp},{ responseType: 'text' });
  }
  resetPassword(email: string, password: string): Observable<any> {
    return this.http.put(`${this.BASEURL}reset-password`, { email, password },{ responseType: 'text' });
  }

  getUsers(): Observable<any[]> {
    return this.http.get<any>(this.ADMINURL+"getuserdetails");
  }
  // grantAccess(email: string): Observable<any> {
  //   return this.http.put(`${this.ADMINURL}access/${email}`, {});
  // }
  
  approveAccess(email: string): Observable<any> {
    return this.http.put<any>(`${this.ADMINURL}giveapproval/${email}`, {});
  }
  
  createTicket(ticketData: any): Observable<any> {  
    return this.http.post<any>(`${this.BASEURL}ticket`,ticketData);
     
      }

      
      getAllTickets(): Observable<any> {
        return this.http.get<any>(`${this.BASEURL}ts`);
      }


      addcontact(data: any, userId: number) {
        return this.http.post<any>(`${this.BASEURL}${userId}/contact`, data);
    }
      getUserId(): number | null {
        const userId = sessionStorage.getItem('id');
        return userId ? +userId : null;
      }


      getContacts(userId:number): Observable<any> {
        return this.http.get<any>(`${this.ADMINURL}${userId}`);
      }

      segmentContacts(userId: number, searchType: string, searchValue: string): Observable<any[]> {
        const url = `${this.BASEURL}${userId}/segmented/${searchType}/${searchValue}`;
        return this.http.get<any[]>(url);
      }
      
    

      segmentAndAssign(requestData: any) {
        return this.http.post<any>(this.LEADURL + 'segmentAndAssign', requestData);
      }
    
      getLeadTrackingsByContactId(leadsId: number) {
        return this.http.get<any>(this.LEADURL + `lead-trackings/contact/${leadsId}`);
      }
    
      updateLeadTrackingStatus(leadsId: number, requestData: any) {
        return this.http.put<any>(this.LEADURL + `updateStatus/${leadsId}`, requestData);
      }
    
      getSalesRepresentativesByCategory(category: string) {
        return this.http.get<any>(this.LEADURL + `sales-representatives/category/${category}`);
      }
    
      getContactsByCategory(category: string) {
        return this.http.get<any>(this.LEADURL + `contacts/category/${category}`);
      }
    
      getAllLeadTrackings() {
        return this.http.get<any>(this.LEADURL + 'lead-trackings');
      }



  }
