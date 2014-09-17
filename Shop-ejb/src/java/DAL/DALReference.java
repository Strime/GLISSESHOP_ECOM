/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Familly;
import Entity.Reference;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sancassani
 */
public class DALReference {
    public static List<Reference> getAllRefsFromFamId(Integer famID,EntityManager em) {
        List<Reference> arr_ref = (List<Reference>)em.createQuery("SELECT r FROM Reference r WHERE r.productidReference.famillyidProduct.idFamilly = "+famID + " OR (r.productidReference.famillyidProduct.famillyidFamilly IS NOT NULL AND r.productidReference.famillyidProduct.famillyidFamilly.idFamilly = "+famID+")").getResultList();
        return arr_ref;
    }
   
    public static Reference getReference(String id,EntityManager em) {
        Reference arr_prod = (Reference)em.createQuery("SELECT r FROM Reference r Where r.idReference = "+id).getResultList().get(0);
        return arr_prod;
    } 

}
