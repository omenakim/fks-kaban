import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroDetailsComponent } from './quadro-details.component';

describe('QuadroDetailsComponent', () => {
  let component: QuadroDetailsComponent;
  let fixture: ComponentFixture<QuadroDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuadroDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuadroDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
