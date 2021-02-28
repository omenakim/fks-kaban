import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MessageService } from 'src/app/core/services/message.service';
import { CriarQuadroRequest } from 'src/app/shared/models/quadro';
import { QuadroService } from 'src/app/shared/services/quadro.service';

@Component({
  selector: 'app-quadro-form',
  templateUrl: './quadro-form.component.html',
  styleUrls: ['./quadro-form.component.css']
})
export class QuadroFormComponent implements OnInit {

  form: FormGroup
  quadroRequest: CriarQuadroRequest

  constructor(private formBuilder: FormBuilder,
    private quadroService: QuadroService,
    private messageService: MessageService,
    public dialogRef: MatDialogRef<QuadroFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.quadroRequest = {
      titulo: undefined,
      descricao: undefined
    }
    this.criarForm();
    this.dialogRef.disableClose = true;
  }

  criarForm() {
    this.form = this.formBuilder.group({
      titulo: [this.quadroRequest.titulo, [Validators.required, Validators.minLength(1), Validators.maxLength(255)]],
      descricao: [this.quadroRequest.descricao, [Validators.minLength(1), Validators.maxLength(5000)]],
    });
  }

  montarRequest() {
    this.quadroRequest = {
      titulo: this.form.value.titulo,
      descricao: this.form.value.descricao
    }
  }

  salvar() {
    this.montarRequest();
    this.quadroService.save(this.quadroRequest).subscribe(() => {
      this.messageService.showMessage(['Quadro criado com sucesso'], 'success');
      this.fechar();
    }, () => {
      this.fechar();
    });
  }

  fechar() {
    this.dialogRef.close();
  }

}
