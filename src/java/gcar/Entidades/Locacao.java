/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcar.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "locacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l"),
    @NamedQuery(name = "Locacao.findByIdLocacao", query = "SELECT l FROM Locacao l WHERE l.locacaoPK.idLocacao = :idLocacao"),
    @NamedQuery(name = "Locacao.findByDataRetirada", query = "SELECT l FROM Locacao l WHERE l.dataRetirada = :dataRetirada"),
    @NamedQuery(name = "Locacao.findByDataDevolucao", query = "SELECT l FROM Locacao l WHERE l.dataDevolucao = :dataDevolucao"),
    @NamedQuery(name = "Locacao.findByAlunoidAluno", query = "SELECT l FROM Locacao l WHERE l.locacaoPK.alunoidAluno = :alunoidAluno"),
    @NamedQuery(name = "Locacao.findByLivroidLivro", query = "SELECT l FROM Locacao l WHERE l.locacaoPK.livroidLivro = :livroidLivro")})
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocacaoPK locacaoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataRetirada")
    @Temporal(TemporalType.DATE)
    private Date dataRetirada;
    @Column(name = "dataDevolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    @JoinColumn(name = "aluno_idAluno", referencedColumnName = "idAluno", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Aluno aluno;
    @JoinColumn(name = "livro_idLivro", referencedColumnName = "idLivro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Livro livro;

    public Locacao() {
    }

    public Locacao(LocacaoPK locacaoPK) {
        this.locacaoPK = locacaoPK;
    }

    public Locacao(LocacaoPK locacaoPK, Date dataRetirada) {
        this.locacaoPK = locacaoPK;
        this.dataRetirada = dataRetirada;
    }

    public Locacao(int idLocacao, int alunoidAluno, int livroidLivro) {
        this.locacaoPK = new LocacaoPK(idLocacao, alunoidAluno, livroidLivro);
    }

    public LocacaoPK getLocacaoPK() {
        return locacaoPK;
    }

    public void setLocacaoPK(LocacaoPK locacaoPK) {
        this.locacaoPK = locacaoPK;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locacaoPK != null ? locacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) object;
        if ((this.locacaoPK == null && other.locacaoPK != null) || (this.locacaoPK != null && !this.locacaoPK.equals(other.locacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gcar.Entidades.Locacao[ locacaoPK=" + locacaoPK + " ]";
    }
    
}
