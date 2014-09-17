/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sancassg
 */
@Entity
@Table(name = "Media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m"),
    @NamedQuery(name = "Media.findByIdMedia", query = "SELECT m FROM Media m WHERE m.idMedia = :idMedia"),
    @NamedQuery(name = "Media.findByFilePath", query = "SELECT m FROM Media m WHERE m.filePath = :filePath")})
public class Media implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedia")
    private Integer idMedia;
    @Size(max = 255)
    @Column(name = "FilePath")
    private String filePath;
    @JoinColumn(name = "DicoMedia_idMedia", referencedColumnName = "idDicoMedia")
    @ManyToOne(fetch = FetchType.EAGER)
    private DicoMedia dicoMediaidMedia;

    public Media() {
    }

    public Media(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public Integer getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public DicoMedia getDicoMediaidMedia() {
        return dicoMediaidMedia;
    }

    public void setDicoMediaidMedia(DicoMedia dicoMediaidMedia) {
        this.dicoMediaidMedia = dicoMediaidMedia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedia != null ? idMedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.idMedia == null && other.idMedia != null) || (this.idMedia != null && !this.idMedia.equals(other.idMedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Media[ idMedia=" + idMedia + " ]";
    }
    
}
