/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import Session.ProductSessionBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("product")
public class ProductService {
    
    @Context
    private UriInfo context;
    
    @EJB
    private ProductSessionBean bean;

    /**
     * Creates a new instance of ProductResource
     */
    public ProductService() {
    }

    /**
     * Retrieves representation of an instance of webservices.ProductService
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
    @Path("getAllOfFamily")
    public String getAllOfFamily(@QueryParam("famID") Integer famID) {
        String res = bean.getAllOfFamily(famID);
        return res;
    }
    
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("getAllOfFamilyFiltered")
    public String getAllOfFamilyFiltered(@QueryParam("famID") Integer famID, @QueryParam("possibleValues") List<String> possibleValues,@QueryParam("typeCaracs") List<Integer> typeCaracs) {
        String res = bean.getAllOfFamilyFiltered(famID, possibleValues, typeCaracs);
        return res;
    }
    
    @GET
    @Produces("application/json")
    @Path("getProduct")
    public String getProduct(@QueryParam("prodID") Integer prodID) {
        String res = bean.getProduct(prodID);
        return res;
    }
    
    
    @POST
    @Produces("application/json")
    @Path("confirmOrder")
    public String confirmOrder(@QueryParam("hash") String hash,
            @QueryParam("mail") String mail,
            @QueryParam("idRef") String items) {
        return bean.confirmOrder(hash,mail,items);
    }
    
}
