import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandpageComponent } from './landpage/landpage.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    {
        path: 'home',
        children: [
            {
                path: '',
                component: LandpageComponent,
            },
            {
                path: 'login',
                component: LoginComponent,
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule { }
