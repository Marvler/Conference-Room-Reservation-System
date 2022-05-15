import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceRoomsListComponent } from './conference-rooms-list.component';

describe('ConferenceRoomsListComponent', () => {
  let component: ConferenceRoomsListComponent;
  let fixture: ComponentFixture<ConferenceRoomsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConferenceRoomsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceRoomsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
