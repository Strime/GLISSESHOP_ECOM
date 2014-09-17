/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Customer;
import Session.AccountSessionBean;
import flexjson.JSON;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wojaka
 */
public class VMCustomer extends VMAbstract<VMCustomer,Customer> {
    
    public Integer id;

    public String Name;

    public String FirstName;
    
    public String Mail;
    
    public String Adress;
    
    public String Hash;
    
    public VMCustomer(Customer customer) {
       id = customer.getIdCustomer();
       Name = customer.getName();
       FirstName = customer.getFirstName();
       Mail = customer.getMail();
       Adress = customer.getAdress();
       Hash = to_hash(customer.getMail() + customer.getPassword());
    }
    
    VMCustomer() {
        super();
    }
    
    public static List<VMCustomer> toVMCustomerList(List<Customer> list) {
        List<VMCustomer> converted = new ArrayList<>();
        for(Customer customer : list) {
            converted.add(new VMCustomer(customer));
        }
        return converted;
    }
    
    @JSON(include=false)
    public static VMCustomer getInstance() {
        return new VMCustomer();
    }
    

    @Override
    public VMCustomer to(Customer element) {
        return new VMCustomer(element);
    }

    private String to_hash(String chaine) {
        String res="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(chaine.getBytes(),0,chaine.length());
            res = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
