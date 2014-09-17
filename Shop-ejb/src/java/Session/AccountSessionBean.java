/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import DAL.DALAccount;
import Entity.Customer;
import ViewModel.VMCustomer;
import flexjson.JSONSerializer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.LocalBean;
import javax.persistence.PersistenceContext;
/**
 *
 * @author wojaka
 */
@Stateless
@LocalBean
public class AccountSessionBean {
    
    private Pattern pattern;
    private Matcher matcher ;

    @PersistenceContext(unitName="Shop-ejbPU")
    EntityManager em;  
    
    public class ErrorImpl {
        public String error = "";
        public ErrorImpl(String message) {
            error = message;
         }
    }
    
    	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public String getAccount(String mail, String password) {
        Customer c = DALAccount.getAccountByMail(mail,em);
        String encrypted = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(),0,password.length());
            encrypted = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(c==null) {  
            JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new ErrorImpl("unknown login"));
        }
        else if(c.getPassword().equals(encrypted)) {
            JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new VMCustomer(c));
        }
        
        JSONSerializer json = new JSONSerializer();
        return json.deepSerialize(new ErrorImpl("bad password"));
        
    }
    
    public String insertAccount(String name, String surname, String adress, String mail, String password) {
        Customer c = new Customer();
        c.setName(name);
        c.setFirstName(surname);
        c.setAdress(adress);
        c.setMail(mail);
        String encrypted = null;
        if(!validateEmail(mail)) {
            JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new ErrorImpl("Bad email"));
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(),0,password.length());
            encrypted = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setPassword(encrypted);
        
        try   
        {
            DALAccount.insertAccount(c, em);
            JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new ErrorImpl("insert OK"));
        }catch (Exception e) {
              JSONSerializer json = new JSONSerializer();
            return json.deepSerialize(new ErrorImpl(e.getMessage()));
        }
        
      
    }
    
    	public boolean validateEmail(final String hex) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(hex);
            return matcher.matches();
 
	}
    
}
