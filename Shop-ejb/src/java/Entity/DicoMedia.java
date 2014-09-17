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
@Table(name = "DicoMedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DicoMedia.findAll", query = "SELECT d FROM DicoMedia d"),
    @NamedQuery(name = "DicoMedia.findByIdDicoMedia", query = "SELECT d FROM DicoMedia d WHERE d.idDicoMedia = :idDicoMedia")})
public class DicoMedia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDicoMedia")
    private Integer idDicoMedia;
    @OneToMany(mappedBy = "dicoMediaidMedia", fetch = FetchType.EAGER)
    private List<Media> mediaList;
    @OneToMany(mappedBy = "dicoMediaidProduct", fetch = FetchType.EAGER)
    private List<Product> productList;

    public DicoMedia() {
    }

    public DicoMedia(Integer idDicoMedia) {
        this.idDicoMedia = idDicoMedia;
    }

    public Integer getIdDicoMedia() {
        return idDicoMedia;
    }

    public void setIdDicoMedia(Integer idDicoMedia) {
        this.idDicoMedia = idDicoMedia;
    }

    @XmlTransient
    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDicoMedia != null ? idDicoMedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DicoMedia)) {
            return false;
        }
        DicoMedia other = (DicoMedia) object;
        if ((this.idDicoMedia == null && other.idDicoMedia != null) || (this.idDicoMedia != null && !this.idDicoMedia.equals(other.idDicoMedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DicoMedia[ idDicoMedia=" + idDicoMedia + " ]";
    }
    
}
