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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sancassg
 */
@Entity
@Table(name = "Carac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carac.findAll", query = "SELECT c FROM Carac c"),
    @NamedQuery(name = "Carac.findByIdCarac", query = "SELECT c FROM Carac c WHERE c.idCarac = :idCarac"),
    @NamedQuery(name = "Carac.findByValue", query = "SELECT c FROM Carac c WHERE c.value = :value")})
public class Carac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCarac")
    private Integer idCarac;
    @Size(max = 255)
    @Column(name = "Value")
    private String value;
    @JoinColumn(name = "TypeCarac_idCarac", referencedColumnName = "idTypeCarac")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeCarac typeCaracidCarac;
    @JoinColumn(name = "Reference_idReference", referencedColumnName = "idReference")
    @ManyToOne(fetch = FetchType.EAGER)
    private Reference referenceidReference;
    @OneToMany(mappedBy = "caracidCarac", fetch = FetchType.EAGER)
    private List<Reference> referenceList;

    public Carac() {
    }

    public Carac(Integer idCarac) {
        this.idCarac = idCarac;
    }

    public Integer getIdCarac() {
        return idCarac;
    }

    public void setIdCarac(Integer idCarac) {
        this.idCarac = idCarac;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeCarac getTypeCaracidCarac() {
        return typeCaracidCarac;
    }

    public void setTypeCaracidCarac(TypeCarac typeCaracidCarac) {
        this.typeCaracidCarac = typeCaracidCarac;
    }

    public Reference getReferenceidReference() {
        return referenceidReference;
    }

    public void setReferenceidReference(Reference referenceidReference) {
        this.referenceidReference = referenceidReference;
    }

    @XmlTransient
    public List<Reference> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<Reference> referenceList) {
        this.referenceList = referenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarac != null ? idCarac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carac)) {
            return false;
        }
        Carac other = (Carac) object;
        if ((this.idCarac == null && other.idCarac != null) || (this.idCarac != null && !this.idCarac.equals(other.idCarac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Carac[ idCarac=" + idCarac + " ]";
    }
    
}
