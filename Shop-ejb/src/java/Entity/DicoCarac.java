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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sancassg
 */
@Entity
@Table(name = "DicoCarac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DicoCarac.findAll", query = "SELECT d FROM DicoCarac d"),
    @NamedQuery(name = "DicoCarac.findByIdDicoCarac", query = "SELECT d FROM DicoCarac d WHERE d.idDicoCarac = :idDicoCarac")})
public class DicoCarac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDicoCarac")
    private Integer idDicoCarac;
    @OneToMany(mappedBy = "dicoCaracidCarac", fetch = FetchType.EAGER)
    private List<TypeCarac> typeCaracList;
    @OneToMany(mappedBy = "dicoCaracidFamilly", fetch = FetchType.EAGER)
    private List<Familly> famillyList;

    public DicoCarac() {
    }

    public DicoCarac(Integer idDicoCarac) {
        this.idDicoCarac = idDicoCarac;
    }

    public Integer getIdDicoCarac() {
        return idDicoCarac;
    }

    public void setIdDicoCarac(Integer idDicoCarac) {
        this.idDicoCarac = idDicoCarac;
    }

    @XmlTransient
    public List<TypeCarac> getTypeCaracList() {
        return typeCaracList;
    }

    public void setTypeCaracList(List<TypeCarac> typeCaracList) {
        this.typeCaracList = typeCaracList;
    }

    @XmlTransient
    public List<Familly> getFamillyList() {
        return famillyList;
    }

    public void setFamillyList(List<Familly> famillyList) {
        this.famillyList = famillyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDicoCarac != null ? idDicoCarac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DicoCarac)) {
            return false;
        }
        DicoCarac other = (DicoCarac) object;
        if ((this.idDicoCarac == null && other.idDicoCarac != null) || (this.idDicoCarac != null && !this.idDicoCarac.equals(other.idDicoCarac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DicoCarac[ idDicoCarac=" + idDicoCarac + " ]";
    }
    
}
