import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomMaskService {

  constructor() { }

  get legalPersonMainDocumentMask(): string {
    return '00.000.000/0000-00';
  }

  get naturalPersonMainDocumentMask(): string {
    return '000.000.000-00';
  }

  get phone(): string {
    return '(00) 00000-0000||(00) 0000-0000';
  }

}
