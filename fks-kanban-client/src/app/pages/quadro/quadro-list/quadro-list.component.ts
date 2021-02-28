import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { QuadroSumario } from 'src/app/shared/models/quadro';
import { QuadroService } from 'src/app/shared/services/quadro.service';
import { QuadroFormComponent } from '../quadro-form/quadro-form.component';

@Component({
  selector: 'app-quadro-list',
  templateUrl: './quadro-list.component.html',
  styleUrls: ['./quadro-list.component.css']
})
export class QuadroListComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['id', 'titulo', 'dataDeCriacao'];
  quadrosDataSource: MatTableDataSource<QuadroSumario>

  totalElements: number;
  pageSize: number;

  @ViewChild(MatPaginator) paginator: MatPaginator

  constructor(
    private quadroService: QuadroService,
    private dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.quadrosDataSource = new MatTableDataSource();
    this.quadrosDataSource.paginator = this.paginator;
  }

  ngAfterViewInit(): void {
    this.buscarQuadros();
    this.paginator.page.pipe(
      tap(() => this.buscarQuadros())
    ).subscribe();
  }

  buscarQuadros() {

    const page: number = this.paginator.pageIndex;
    const size: number = this.paginator.pageSize;

    this.quadroService.listarQuadrosDoUsuarioLogado(page, size).subscribe(response => {
      this.totalElements = response["totalElements"]
      this.pageSize = response["size"];
      this.quadrosDataSource = new MatTableDataSource(response['content']);
    })
  }

  abrirQuadroForm() {
    const dialogRef = this.dialog.open(QuadroFormComponent, {
      height: '50vh',
      width: '600px',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.buscarQuadros();
    });
  }

  navegarParaDetalhes(row: any) {
    this.router.navigate([`quadros/${row.id}`]);
  }

}
