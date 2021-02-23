import { Component, OnInit } from '@angular/core';
import { QuadroSumario } from 'src/app/shared/models/quadro';
import { QuadroService } from 'src/app/shared/services/quadro.service';

@Component({
  selector: 'app-quadro-list',
  templateUrl: './quadro-list.component.html',
  styleUrls: ['./quadro-list.component.css']
})
export class QuadroListComponent implements OnInit {

  quadros: QuadroSumario[];

  constructor(
    private quadroService: QuadroService
  ) { }

  ngOnInit(): void {
    this.quadros = [];
    this.quadroService.findAllThatUserBelongs().subscribe(response => {
      this.quadros = response['content']
    })
  }

}
