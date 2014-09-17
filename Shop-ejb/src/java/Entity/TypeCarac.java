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
@Table(name = "TypeCarac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeCarac.findAll", query = "SELECT t FROM TypeCarac t"),
    @NamedQuery(name = "TypeCarac.findByIdTypeCarac", query = "SELECT t FROM TypeCarac t WHERE t.idTypeCarac = :idTypeCarac"),
    @NamedQuery(name = "TypeCarac.findByLabel", query = "SELECT t FROM TypeCarac t WHERE t.label = :label")})
public class TypeCarac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTypeCarac")
    private Integer idTypeCarac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Label")
    private String label;
    @JoinColumn(name = "DicoCarac_idCarac", referencedColumnName = "idDicoCarac")
    @ManyToOne(fetch = FetchType.EAGER)
    private DicoCarac dicoCaracidCarac;
    @OneToMany(mappedBy = "typeCaracidCarac", fetch = FetchType.EAGER)
    private List<Carac> caracList;

    public TypeCarac() {
    }

    public TypeCarac(Integer idTypeCarac) {
        this.idTypeCarac = idTypeCarac;
    }

    public TypeCarac(Integer idTypeCarac, String label) {
        this.idTypeCarac = idTypeCarac;
        this.label = label;
    }

    public Integer getIdTypeCarac() {
        return idTypeCarac;
    }

    public void setIdTypeCarac(Integer idTypeCarac) {
        this.idTypeCarac = idTypeCarac;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DicoCarac getDicoCaracidCarac() {
        return dicoCaracidCarac;
    }

    public void setDicoCaracidCarac(DicoCarac dicoCaracidCarac) {
        this.dicoCaracidCarac = dicoCaracidCarac;
    }

    @XmlTransient
    public List<Carac> getCaracList() {
        return caracList;
    }

    public void setCaracList(List<Carac> caracList) {
        this.caracList = caracList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeCarac != null ? idTypeCarac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeCarac)) {
            return false;
        }
        TypeCarac other = (TypeCarac) object;
        if ((this.idTypeCarac == null && other.idTypeCarac != null) || (this.idTypeCarac != null && !this.idTypeCarac.equals(other.idTypeCarac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TypeCarac[ idTypeCarac=" + idTypeCarac + " ]";
    }
    
}
