/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import DAL.DALReference;
import Entity.Reference;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sancassg
 */
@Stateless
@LocalBean
public class ReferenceSessionbean {
    @PersistenceContext(unitName="Shop-ejbPU")
    EntityManager em;
    
     public List<Reference> getAllRefsFromFamId(Integer famID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Shop-ejbPU");
        EntityManager em = emf.createEntityManager();
        return DALReference.getAllRefsFromFamId(famID, em);
     }
}
