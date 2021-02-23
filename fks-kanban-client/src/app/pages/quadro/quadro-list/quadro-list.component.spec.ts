import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroListComponent } from './quadro-list.component';

describe('QuadroListComponent', () => {
  let component: QuadroListComponent;
  let fixture: ComponentFixture<QuadroListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuadroListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuadroListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
