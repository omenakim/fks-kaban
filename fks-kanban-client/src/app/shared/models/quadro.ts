import { usuario } from "./usuario";

export interface Quadro {
    id?: number;
    titulo?: string;
    descricao?: string;
    dono?: usuario;
    dataDeCriacao?: Date;
    membros?: usuario[];
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