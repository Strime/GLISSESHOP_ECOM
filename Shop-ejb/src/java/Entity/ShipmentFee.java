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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sancassg
 */
@Entity
@Table(name = "ShipmentFee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipmentFee.findAll", query = "SELECT s FROM ShipmentFee s"),
    @NamedQuery(name = "ShipmentFee.findByIdShipmentFee", query = "SELECT s FROM ShipmentFee s WHERE s.idShipmentFee = :idShipmentFee"),
    @NamedQuery(name = "ShipmentFee.findByProductidProduct", query = "SELECT s FROM ShipmentFee s WHERE s.productidProduct = :productidProduct")})
public class ShipmentFee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idShipmentFee")
    private Integer idShipmentFee;
    @Column(name = "Product_idProduct")
    private Integer productidProduct;
    @JoinColumn(name = "Supplier_idSupplier", referencedColumnName = "idSupplier")
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplieridSupplier;

    public ShipmentFee() {
    }

    public ShipmentFee(Integer idShipmentFee) {
        this.idShipmentFee = idShipmentFee;
    }

    public Integer getIdShipmentFee() {
        return idShipmentFee;
    }

    public void setIdShipmentFee(Integer idShipmentFee) {
        this.idShipmentFee = idShipmentFee;
    }

    public Integer getProductidProduct() {
        return productidProduct;
    }

    public void setProductidProduct(Integer productidProduct) {
        this.productidProduct = productidProduct;
    }

    public Supplier getSupplieridSupplier() {
        return supplieridSupplier;
    }

    public void setSupplieridSupplier(Supplier supplieridSupplier) {
        this.supplieridSupplier = supplieridSupplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idShipmentFee != null ? idShipmentFee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShipmentFee)) {
            return false;
        }
        ShipmentFee other = (ShipmentFee) object;
        if ((this.idShipmentFee == null && other.idShipmentFee != null) || (this.idShipmentFee != null && !this.idShipmentFee.equals(other.idShipmentFee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ShipmentFee[ idShipmentFee=" + idShipmentFee + " ]";
    }
    
}
