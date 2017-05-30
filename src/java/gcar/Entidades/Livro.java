/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcar.Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "livro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l"),
    @NamedQuery(name = "Livro.findByIdLivro", query = "SELECT l FROM Livro l WHERE l.idLivro = :idLivro"),
    @NamedQuery(name = "Livro.findByTituloLivro", query = "SELECT l FROM Livro l WHERE l.tituloLivro = :tituloLivro"),
    @NamedQuery(name = "Livro.findByAutorLivro", query = "SELECT l FROM Livro l WHERE l.autorLivro = :autorLivro"),
    @NamedQuery(name = "Livro.findByQuantidadeEstoque", query = "SELECT l FROM Livro l WHERE l.quantidadeEstoque = :quantidadeEstoque")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLivro")
    private Integer idLivro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tituloLivro")
    private String tituloLivro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "autorLivro")
    private String autorLivro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidadeEstoque")
    private int quantidadeEstoque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livro")
    private Collection<Locacao> locacaoCollection;

    public Livro() {
    }

    public Livro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Livro(Integer idLivro, String tituloLivro, String autorLivro, int quantidadeEstoque) {
        this.idLivro = idLivro;
        this.tituloLivro = tituloLivro;
        this.autorLivro = autorLivro;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @XmlTransient
    public Collection<Locacao> getLocacaoCollection() {
        return locacaoCollection;
    }

    public void setLocacaoCollection(Collection<Locacao> locacaoCollection) {
        this.locacaoCollection = locacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tituloLivro;
    }    
}
