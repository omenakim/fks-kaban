import { Usuario } from "./usuario";

export interface Quadro {
    id?: number;
    titulo?: string;
    descricao?: string;
    dono?: Usuario;
    dataDeCriacao?: Date;
    membros?: Usuario[];
}

export interface QuadroSumario {
    id?: number;
    titulo?: string;
    dataDeCriacao?: Date;
}

export interface CriarQuadroRequest {
    titulo?: string;
    descricao?: string;
}