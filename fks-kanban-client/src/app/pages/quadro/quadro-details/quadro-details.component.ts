import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Quadro } from 'src/app/shared/models/quadro';
import { QuadroService } from 'src/app/shared/services/quadro.service';
import { QuadroInfoComponent } from '../quadro-info/quadro-info.component';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-quadro-details',
  templateUrl: './quadro-details.component.html',
  styleUrls: ['./quadro-details.component.css']
})
export class QuadroDetailsComponent implements OnInit {

  id: number;
  quadro: Quadro = {};

  todo = [
    'Get to work',
    'Pick up groceries',
    'Go home',
    'Fall asleep'
  ];

  done = [
    'Get up',
    'Brush teeth',
    'Take a shower',
    'Check e-mail',
    'Walk dog'
  ];

  constructor(
    private quadroService: QuadroService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.quadroService.buscarPorId(this.id).subscribe(response => {
      this.quadro = response;
    });
  }

  abrirQuadroInfo() {
    const dialogRef = this.dialog.open(QuadroInfoComponent, {
      width: '900px',
      data: this.quadro
    });
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
  }


}
