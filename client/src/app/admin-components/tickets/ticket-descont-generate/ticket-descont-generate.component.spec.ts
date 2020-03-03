import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketDescontGenerateComponent } from './ticket-descont-generate.component';

describe('TicketDescontGenerateComponent', () => {
  let component: TicketDescontGenerateComponent;
  let fixture: ComponentFixture<TicketDescontGenerateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketDescontGenerateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketDescontGenerateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
