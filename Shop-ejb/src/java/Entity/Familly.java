/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author sancassg
 */
@Entity
@Table(name = "Familly")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familly.findAll", query = "SELECT f FROM Familly f"),
    @NamedQuery(name = "Familly.findByIdFamilly", query = "SELECT f FROM Familly f WHERE f.idFamilly = :idFamilly"),
    @NamedQuery(name = "Familly.findByLabel", query = "SELECT f FROM Familly f WHERE f.label = :label")})
public class Familly implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFamilly")
    private Integer idFamilly;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Label")
    private String label;
    @OneToMany(mappedBy = "famillyidProduct", fetch = FetchType.EAGER)
    private List<Product> productList;
    @OneToMany(mappedBy = "famillyidFamilly", fetch = FetchType.EAGER)
    private List<Familly> famillyList;
    @JoinColumn(name = "Familly_idFamilly", referencedColumnName = "idFamilly")
    @ManyToOne(fetch = FetchType.EAGER)
    private Familly famillyidFamilly;
    @JoinColumn(name = "DicoCarac_idFamilly", referencedColumnName = "idDicoCarac")
    @ManyToOne(fetch = FetchType.EAGER)
    private DicoCarac dicoCaracidFamilly;

    public Familly() {
    }

    public Familly(Integer idFamilly) {
        this.idFamilly = idFamilly;
    }

    public Familly(Integer idFamilly, String label) {
        this.idFamilly = idFamilly;
        this.label = label;
    }

    public Integer getIdFamilly() {
        return idFamilly;
    }

    public void setIdFamilly(Integer idFamilly) {
        this.idFamilly = idFamilly;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @XmlTransient
    public List<Familly> getFamillyList() {
        return famillyList;
    }

    public void setFamillyList(List<Familly> famillyList) {
        this.famillyList = famillyList;
    }

    public Familly getFamillyidFamilly() {
        return famillyidFamilly;
    }

    public void setFamillyidFamilly(Familly famillyidFamilly) {
        this.famillyidFamilly = famillyidFamilly;
    }

    public DicoCarac getDicoCaracidFamilly() {
        return dicoCaracidFamilly;
    }

    public void setDicoCaracidFamilly(DicoCarac dicoCaracidFamilly) {
        this.dicoCaracidFamilly = dicoCaracidFamilly;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilly != null ? idFamilly.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familly)) {
            return false;
        }
        Familly other = (Familly) object;
        if ((this.idFamilly == null && other.idFamilly != null) || (this.idFamilly != null && !this.idFamilly.equals(other.idFamilly))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Familly[ idFamilly=" + idFamilly + " ]";
    }
    
}
