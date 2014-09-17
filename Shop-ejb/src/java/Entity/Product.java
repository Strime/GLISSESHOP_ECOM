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
@Table(name = "Product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByIdProduct", query = "SELECT p FROM Product p WHERE p.idProduct = :idProduct"),
    @NamedQuery(name = "Product.findByLabel", query = "SELECT p FROM Product p WHERE p.label = :label"),
    @NamedQuery(name = "Product.findByLongLabel", query = "SELECT p FROM Product p WHERE p.longLabel = :longLabel"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProduct")
    private Integer idProduct;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Label")
    private String label;
    @Size(max = 255)
    @Column(name = "LongLabel")
    private String longLabel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @JoinColumn(name = "Familly_idProduct", referencedColumnName = "idFamilly")
    @ManyToOne(fetch = FetchType.EAGER)
    private Familly famillyidProduct;
    @JoinColumn(name = "DicoMedia_idProduct", referencedColumnName = "idDicoMedia")
    @ManyToOne(fetch = FetchType.EAGER)
    private DicoMedia dicoMediaidProduct;
    @OneToMany(mappedBy = "productidReference", fetch = FetchType.EAGER)
    private List<Reference> referenceList;

    public Product() {
    }

    public Product(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Product(Integer idProduct, String label, int price) {
        this.idProduct = idProduct;
        this.label = label;
        this.price = price;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLongLabel() {
        return longLabel;
    }

    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Familly getFamillyidProduct() {
        return famillyidProduct;
    }

    public void setFamillyidProduct(Familly famillyidProduct) {
        this.famillyidProduct = famillyidProduct;
    }

    public DicoMedia getDicoMediaidProduct() {
        return dicoMediaidProduct;
    }

    public void setDicoMediaidProduct(DicoMedia dicoMediaidProduct) {
        this.dicoMediaidProduct = dicoMediaidProduct;
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
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Product[ idProduct=" + idProduct + " ]";
    }
    
}
