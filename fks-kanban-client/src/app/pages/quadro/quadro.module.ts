import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { QuadroListComponent } from './quadro-list/quadro-list.component';
import { QuadroRoutingModule } from './quadro-routing.module';



@NgModule({
  declarations: [
    QuadroListComponent
  ],
  imports: [
    SharedModule,
    QuadroRoutingModule
  ]
})
export class QuadroModule { }
