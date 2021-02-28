import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Quadro } from 'src/app/shared/models/quadro';
import { QuadroService } from 'src/app/shared/services/quadro.service';
import { QuadroInfoComponent } from '../quadro-info/quadro-info.component';

@Component({
  selector: 'app-quadro-details',
  templateUrl: './quadro-details.component.html',
  styleUrls: ['./quadro-details.component.css']
})
export class QuadroDetailsComponent implements OnInit {

  id: number;
  quadro: Quadro = {};

  constructor(
    private quadroService: QuadroService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.quadroService.findById(this.id).subscribe(response => {
      this.quadro = response;
    });

  }

  openQuadroInfo() {
    const dialogRef = this.dialog.open(QuadroInfoComponent, {
      width: '900px',
      data: this.quadro
    });
  }


}
