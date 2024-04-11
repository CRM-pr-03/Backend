import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LeadtrackingComponent } from './leadtracking.component';
describe('LeadtrackingComponent', () => {
  let component: LeadtrackingComponent;
  let fixture: ComponentFixture<LeadtrackingComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LeadtrackingComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(LeadtrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});