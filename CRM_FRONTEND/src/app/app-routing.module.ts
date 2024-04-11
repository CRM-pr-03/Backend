import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer.component';
import { AddcontactComponent } from './addcontact/addcontact.component';
import { UpdatecontactComponent } from './updatecontact/updatecontact.component';
import { ContactsegmentationComponent } from './contactsegmentation/contactsegmentation.component';
import { LeadtrackingComponent } from './leadtracking/leadtracking.component';
import { AddupdateComponent } from './addupdate/addupdate.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'footer', component: FooterComponent },
  { path: 'nav', component: NavbarComponent },
  {path:'signup',component:SignupComponent},
  {path:'sidebar',component:SidebarComponent},
  {path:'customer',component:CustomerComponent},
  {path:'addupdate',component:AddupdateComponent},
  {path:'updatecontact',component:UpdatecontactComponent},
  {path:'customersegmentation',component:ContactsegmentationComponent},
{path:'leadtracking',component:LeadtrackingComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
