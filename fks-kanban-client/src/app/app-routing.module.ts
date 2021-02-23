import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotAuthorizedPageComponent } from './shared/components/not-authorized-page/not-authorized-page.component';
import { NotFoundPageComponent } from './shared/components/not-found-page/not-found-page.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', loadChildren: './modules/home/home.module#HomeModule' },
  { path: '**', component: NotFoundPageComponent },
  { path: 'not-authorized', component: NotAuthorizedPageComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }