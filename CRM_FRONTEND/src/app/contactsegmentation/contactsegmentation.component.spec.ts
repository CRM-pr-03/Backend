import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ContactsegmentationComponent } from './contactsegmentation.component';
describe('ContactsegmentationComponent', () => {
  let component: ContactsegmentationComponent;
  let fixture: ComponentFixture<ContactsegmentationComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContactsegmentationComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(ContactsegmentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});