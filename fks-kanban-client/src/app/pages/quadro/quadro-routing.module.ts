import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { QuadroDetailsComponent } from './quadro-details/quadro-details.component';
import { QuadroListComponent } from './quadro-list/quadro-list.component';

const routes: Routes = [
    {
        path: 'quadros',
        children: [
            {
                path: '',
                component: QuadroListComponent,
                canActivate: [AuthGuard],
            },
            {
                path: ':id',
                component: QuadroDetailsComponent,
                canActivate: [AuthGuard],
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class QuadroRoutingModule { }
