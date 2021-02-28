import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { QuadroListComponent } from './quadro-list/quadro-list.component';
import { QuadroRoutingModule } from './quadro-routing.module';
import { QuadroFormComponent } from './quadro-form/quadro-form.component';
import { QuadroDetailsComponent } from './quadro-details/quadro-details.component';
import { QuadroInfoComponent } from './quadro-info/quadro-info.component';



@NgModule({
  declarations: [
    QuadroListComponent,
    QuadroFormComponent,
    QuadroDetailsComponent,
    QuadroInfoComponent
  ],
  imports: [
    SharedModule,
    QuadroRoutingModule
  ]
})
export class QuadroModule { }
