import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
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
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.quadrosDataSource = new MatTableDataSource();
    this.quadrosDataSource.paginator = this.paginator;
  }

  ngAfterViewInit(): void {
    this.loadQuadros();
    this.paginator.page.pipe(
      tap(()=> this.loadQuadros())
    ).subscribe();
  }

  loadQuadros(){
    this.quadroService.findAllThatUserBelongs(this.paginator.pageIndex, this.paginator.pageSize).subscribe(response => {
      console.log(response)
      this.totalElements = response["totalElements"]
      this.pageSize = response["size"];
      this.quadrosDataSource = new MatTableDataSource(response['content']);
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
