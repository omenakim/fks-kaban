import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { QuadroSumario } from 'src/app/shared/models/quadro';
import { QuadroService } from 'src/app/shared/services/quadro.service';
import { QuadroFormComponent } from '../quadro-form/quadro-form.component';

@Component({
  selector: 'app-quadro-list',
  templateUrl: './quadro-list.component.html',
  styleUrls: ['./quadro-list.component.css']
})
export class QuadroListComponent implements OnInit {

  quadros: QuadroSumario[];

  constructor(
    private quadroService: QuadroService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.quadros = [];
    this.loadQuadros();
  }

  loadQuadros(){
    this.quadroService.findAllThatUserBelongs().subscribe(response => {
      this.quadros = response['content']
    })
  }

  openQuadroForm(){
    const dialogRef = this.dialog.open(QuadroFormComponent, {
      height: '50vh',
      width: '600px',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.loadQuadros();
    });
  }

}
