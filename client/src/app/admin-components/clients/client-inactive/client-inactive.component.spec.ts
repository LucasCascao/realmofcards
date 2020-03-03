import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientInactiveComponent } from './client-inactive.component';

describe('ClientInactiveComponent', () => {
  let component: ClientInactiveComponent;
  let fixture: ComponentFixture<ClientInactiveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientInactiveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientInactiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
