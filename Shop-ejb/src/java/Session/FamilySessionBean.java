/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Session;

import DAL.DALFamily;
import Entity.Familly;
import ViewModel.VMFamily;
import flexjson.JSONSerializer;
import java.util.List;
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
public class FamilySessionBean {
    
    @PersistenceContext(unitName="Shop-ejbPU")
    EntityManager em;

    public String getFamily(Integer id) {
        Familly fam = DALFamily.getFamily(id,em);
        
        JSONSerializer json = new JSONSerializer();
        return json.deepSerialize(new VMFamily(fam));
    }
    
    public String getAll() {
        List<Familly> arr_fam = DALFamily.getFirstLevel(em);
        List<VMFamily> vm = VMFamily.toVMFamilyList(arr_fam);
        return vm.toString();
    }
}
