/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import DAL.DALAccount;
import DAL.DALOrder;
import DAL.DALProduct;
import DAL.DALReference;
import Entity.Customer;
import Entity.Item;
import Entity.Orders;
import Entity.Product;
import Entity.Reference;
import ViewModel.VMProduct;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anton
 */
@Stateless
@LocalBean
public class ProductSessionBean {

    public String getCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public class Zeub {
        public String idRef;
        public String Count;
    }
    
    public class ErrorImpl {
        public String error = "";
        public ErrorImpl(String message) {
            error = message;
         }
    }
    
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
    
        public String confirmOrder(String hash, String mail,String items) {
            
        Customer compte = DALAccount.getAccountByMail(mail, em);
        
        if(!cryp(compte.getMail()+compte.getPassword()).equals(hash)){
            JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new ErrorImpl("Error HASH ! HACK SPOTTED"));
        }
        
        
        
        Orders order = new Orders();
        order.setCustomeridOrders(compte);
        
        try   
        {
            DALOrder.insertOrder(order, em);
        }catch (Exception e) {
            JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new ErrorImpl("Erreur BD ORDER: "+e.getMessage()));
        }
        
        
        if(!items.startsWith("[")){
            items = "[" + items + "]";
        }
        
        java.lang.reflect.Type listType = new TypeToken<ArrayList<Zeub>>(){}.getType();
        List<Zeub> listOfItems = new Gson().fromJson(items, listType);

        
        for (Zeub listOfItem : listOfItems) {
            Reference r = DALReference.getReference(listOfItem.idRef, em);
            Item item = new Item();
            item.setCount(Integer.parseInt(listOfItem.Count));
            item.setIdRef(r);
            item.setOrderidOrder(order);
            try   
            {
                DALOrder.insertItem(item,em);
            }catch (Exception e) {
                JSONSerializer json = new JSONSerializer();
                return json.deepSerialize(new ErrorImpl("Erreur BD ITEM: "+e.getMessage()));
            }
        }
        
        JSONSerializer json = new JSONSerializer();
        return json.deepSerialize(new ErrorImpl("insert OK"));
    }
    
    public String cryp(String password){
        String encrypted="";
         try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(),0,password.length());
            encrypted = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encrypted;
    }

}
