import { TestBed } from '@angular/core/testing';

import { OrgaznizationServiceService } from './orgaznization-service.service';

describe('OrgaznizationServiceService', () => {
  let service: OrgaznizationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrgaznizationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
