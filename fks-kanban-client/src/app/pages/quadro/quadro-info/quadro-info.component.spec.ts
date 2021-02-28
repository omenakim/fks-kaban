import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroInfoComponent } from './quadro-info.component';

describe('QuadroInfoComponent', () => {
  let component: QuadroInfoComponent;
  let fixture: ComponentFixture<QuadroInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuadroInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuadroInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
