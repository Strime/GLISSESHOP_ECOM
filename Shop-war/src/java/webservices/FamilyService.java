/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import Session.FamilySessionBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author anton
 */
@Stateless
@Path("family")
public class FamilyService {

    @Context
    private UriInfo context;
    
    @EJB
    private FamilySessionBean bean;

    /**
     * Creates a new instance of RestResource
     */
    public FamilyService() {
    }

    /**
     * Retrieves representation of an instance of webservices.FamilyService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("getAll")
    public String getAll() {
        String res = bean.getAll();
        return res;
    }
    
    @GET
    @Produces("application/json")
    @Path("getFamily")
    public String getFamily(@QueryParam("famID") Integer famID) {
        String res = bean.getFamily(famID);
        return res;
    }
}
