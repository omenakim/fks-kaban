import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { HomeRoutingModule } from './home-routing.module';
import { LandpageComponent } from './landpage/landpage.component';
import { LoginComponent } from './login/login.component';



@NgModule({
  declarations: [
    LandpageComponent, 
    LoginComponent, 
  ],
  imports: [
    SharedModule,
    HomeRoutingModule
  ]
})
export class HomeModule { }
