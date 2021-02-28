import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { AuthService } from 'src/app/core/services/auth.service';
import { Quadro } from 'src/app/shared/models/quadro';
import { Usuario } from 'src/app/shared/models/usuario';
import { QuadroService } from 'src/app/shared/services/quadro.service';

@Component({
  selector: 'app-quadro-info',
  templateUrl: './quadro-info.component.html',
  styleUrls: ['./quadro-info.component.css']
})
export class QuadroInfoComponent implements OnInit {

  quadro: Quadro;
  options: Usuario[];
  filteredOptions: Observable<Usuario[]>;
  usuarioFormControl = new FormControl();
  usuarioLogado: string;

  displayedColumns: string[] = ['id', 'username', 'actions'];
  membrosDataSource: MatTableDataSource<Usuario>

  constructor(
    private authService: AuthService,
    private quadroService: QuadroService,
    public dialogRef: MatDialogRef<QuadroInfoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Quadro) { }

  ngOnInit(): void {
    this.dialogRef.disableClose = true;
    this.quadro = this.data;
    this.loadMembros();
    this.loadNaoMembros();
    this.initFilteredOptionsListener();
    this.usuarioLogado = this.authService.jwtPayload.user_name;
  }

  addMembro() {
    console.log(this.usuarioFormControl.value)
  }

  removeMembro(usuario: Usuario) {
    console.log(usuario)
  }

  loadMembros() {
    this.membrosDataSource = new MatTableDataSource(this.quadro.membros);
  }

  loadNaoMembros() {
    this.options = [];
    this.quadroService.findNaoMembrosByQuadroId(this.quadro.id).subscribe(response => {
      this.options = response;
    });
  }

  initFilteredOptionsListener() {
    this.filteredOptions = this.usuarioFormControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof value === 'string' ? value : value.username),
        map(username => username ? this.filterByUsername(username) : this.options.slice())
      );
  }

  private filterByUsername(username: string): Usuario[] {
    const filterValue = username.toLowerCase();
    return this.options.filter(option => option.username.toLowerCase().indexOf(filterValue) === 0);
  }

  close() {
    this.dialogRef.close();
  }

  displayUsername(usuario: Usuario): string {
    return usuario && usuario.username ? usuario.username : '';
  }

}
