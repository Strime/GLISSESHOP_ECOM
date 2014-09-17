/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sancassg
 */
@Entity
@Table(name = "Reference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reference.findAll", query = "SELECT r FROM Reference r"),
    @NamedQuery(name = "Reference.findByIdReference", query = "SELECT r FROM Reference r WHERE r.idReference = :idReference")})
public class Reference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReference")
    private Integer idReference;
    @OneToMany(mappedBy = "referenceidReference", fetch = FetchType.EAGER)
    private List<Stock> stockList;
    @OneToMany(mappedBy = "referenceidReference", fetch = FetchType.EAGER)
    private List<Carac> caracList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRef", fetch = FetchType.EAGER)
    private List<Item> itemList;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem")
    @ManyToOne(fetch = FetchType.EAGER)
    private Item itemidItem;
    @JoinColumn(name = "Carac_idCarac", referencedColumnName = "idCarac")
    @ManyToOne(fetch = FetchType.EAGER)
    private Carac caracidCarac;
    @JoinColumn(name = "Product_idReference", referencedColumnName = "idProduct")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product productidReference;

    public Reference() {
    }

    public Reference(Integer idReference) {
        this.idReference = idReference;
    }

    public Integer getIdReference() {
        return idReference;
    }

    public void setIdReference(Integer idReference) {
        this.idReference = idReference;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @XmlTransient
    public List<Carac> getCaracList() {
        return caracList;
    }

    public void setCaracList(List<Carac> caracList) {
        this.caracList = caracList;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(Item itemidItem) {
        this.itemidItem = itemidItem;
    }

    public Carac getCaracidCarac() {
        return caracidCarac;
    }

    public void setCaracidCarac(Carac caracidCarac) {
        this.caracidCarac = caracidCarac;
    }

    public Product getProductidReference() {
        return productidReference;
    }

    public void setProductidReference(Product productidReference) {
        this.productidReference = productidReference;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReference != null ? idReference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reference)) {
            return false;
        }
        Reference other = (Reference) object;
        if ((this.idReference == null && other.idReference != null) || (this.idReference != null && !this.idReference.equals(other.idReference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Reference[ idReference=" + idReference + " ]";
    }
    
}
