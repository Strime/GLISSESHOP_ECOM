/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import DAL.DALProduct;
import Entity.Product;
import ViewModel.VMProduct;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anton
 */
@Stateless
@LocalBean
public class ProductSessionBean {
    
    public class VMCaracT {
        
        public VMCaracT() {
            
        }
        
        public Integer type;
        public List<String> values;
    }
    
    public class Wrapper {
        public Wrapper() {
            
        }
        public List<VMCaracT> items;
    }

    @PersistenceContext(unitName="Shop-ejbPU")
    EntityManager em;
    
    public String getProduct(Integer id) {
        Product prod = DALProduct.getProduct(id,em);
        
        JSONSerializer json = new JSONSerializer();
        return json.deepSerialize(new VMProduct(prod));
    }
    
    public String getAll() {
        List<Product> arr_prod = DALProduct.getAll(em);
        List<VMProduct> vm = VMProduct.getInstance().toList(arr_prod);
        return vm.toString();
    }

    public String getAllOfFamily(Integer famID) {
        List<Product> arr_prod = DALProduct.getFromFamily(famID,em);
        List<VMProduct> vm = VMProduct.getInstance().toList(arr_prod);
        return vm.toString();
    }
    
    /**
     *
     * @param famID
     * @param filters
     * @return
     */
    public String getAllOfFamilyFiltered(Integer famID, List<String> possibleValues, List<Integer> typeCaracs) {
        List<VMCaracT> compactFilters = null;
        List<VMCaracT> wp = null;
        
        
       //wp = (List<VMCaracT>) new JSONDeserializer<Map<VMCaracT, List<VMCaracT>>>().deserialize(filters);
        List<Product> arr_prod = DALProduct.getFromFamilyFiltered(famID,possibleValues,typeCaracs,em);
        List<VMProduct> vm = VMProduct.getInstance().toList(arr_prod);
        return vm.toString();
    }
    
    public String getAllProductsByKeyword(String keyword) {
        List<Product> arr_prod = DALProduct.getByKeyword(keyword,em);
        List<VMProduct> vm = VMProduct.getInstance().toList(arr_prod);
        return vm.toString();
    }
}
