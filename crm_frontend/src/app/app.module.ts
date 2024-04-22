import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatSidenavModule} from '@angular/material/sidenav';
import { MatButtonModule} from '@angular/material/button';
import { MatIconModule} from '@angular/material/icon';
import {  MatDividerModule} from '@angular/material/divider';
import { HttpClient, HttpClientModule, provideHttpClient } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';

import { AboutComponent } from './about/about.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { OnboardComponent } from './onboard/onboard.component';

import { ReportsComponent } from './reports/reports.component';
import { SupportComponent } from './support/support.component';
import { AddcontactComponent } from './addcontact/addcontact.component';
import { ExcelreportComponent } from './excelreport/excelreport.component';
import { UpdatecontactComponent } from './updatecontact/updatecontact.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { LeadTrackingComponent } from './lead-tracking/lead-tracking.component';
import { AuthService } from './auth.service';
import { NotfoundComponent } from './notfound/notfound.component';
//  import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { RolemanagementComponent } from './rolemanagement/rolemanagement.component';
import { UpdateLeadsComponent } from './update-leads/update-leads.component';
import { OpportunityComponent } from './opportunity/opportunity.component';






@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    AboutComponent,
    ForgotPasswordComponent,
    UserComponent,
    AdminComponent,
    AdminLoginComponent,
    OnboardComponent,
   
    ReportsComponent,
         SupportComponent,
         AddcontactComponent,
         ExcelreportComponent,
         UpdatecontactComponent,
         LeadTrackingComponent,
         NotfoundComponent,
         RolemanagementComponent,
         UpdateLeadsComponent,
         OpportunityComponent,
        


  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatDatepickerModule,
    

    ToastrModule.forRoot(),
    
  ],
 
  providers: [
    AuthService,
    // { provide: LocationStrategy, useClass: HashLocationStrategy },
    provideClientHydration(),
    provideAnimationsAsync(),
    { provide: 'HttpConfigOptions', useValue: { useFetch: true } }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
