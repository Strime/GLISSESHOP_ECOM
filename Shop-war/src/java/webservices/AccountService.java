/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import Session.AccountSessionBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author wojaka
 */
@Stateless
@Path("account")
public class AccountService {

    @Context
    private UriInfo context;
    
    @EJB
    private AccountSessionBean bean;

    /**
     * Creates a new instance of AccountService
     */
    public AccountService() {
    }

    @GET
    @Produces("application/json")
    @Path("getAccount")
    public String getAccount(@QueryParam("mail") String mail,@QueryParam("password") String password) {
        String res = bean.getAccount(mail,password);
        return res;
    }

    @POST
    @Produces("application/json")
    @Path("setAccount")
    public String setAccount(@QueryParam("name") String name,@QueryParam("surname") String surname,
            @QueryParam("adress") String adress,
            @QueryParam("mail") String mail,
            @QueryParam("password") String password) {
        return bean.insertAccount(name,surname,adress,mail,password);
    }
}
