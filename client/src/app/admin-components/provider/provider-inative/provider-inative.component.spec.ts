import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderInativeComponent } from './provider-inative.component';

describe('ProviderInativeComponent', () => {
  let component: ProviderInativeComponent;
  let fixture: ComponentFixture<ProviderInativeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProviderInativeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProviderInativeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
