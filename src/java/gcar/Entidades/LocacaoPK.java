/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcar.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
@Embeddable
public class LocacaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idLocacao")
    private int idLocacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aluno_idAluno")
    private int alunoidAluno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "livro_idLivro")
    private int livroidLivro;

    public LocacaoPK() {
    }

    public LocacaoPK(int idLocacao, int alunoidAluno, int livroidLivro) {
        this.idLocacao = idLocacao;
        this.alunoidAluno = alunoidAluno;
        this.livroidLivro = livroidLivro;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getAlunoidAluno() {
        return alunoidAluno;
    }

    public void setAlunoidAluno(int alunoidAluno) {
        this.alunoidAluno = alunoidAluno;
    }

    public int getLivroidLivro() {
        return livroidLivro;
    }

    public void setLivroidLivro(int livroidLivro) {
        this.livroidLivro = livroidLivro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLocacao;
        hash += (int) alunoidAluno;
        hash += (int) livroidLivro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocacaoPK)) {
            return false;
        }
        LocacaoPK other = (LocacaoPK) object;
        if (this.idLocacao != other.idLocacao) {
            return false;
        }
        if (this.alunoidAluno != other.alunoidAluno) {
            return false;
        }
        if (this.livroidLivro != other.livroidLivro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gcar.Entidades.LocacaoPK[ idLocacao=" + idLocacao + ", alunoidAluno=" + alunoidAluno + ", livroidLivro=" + livroidLivro + " ]";
    }
    
}
