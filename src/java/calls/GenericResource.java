/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Harsh
 */
@Path("generic")
public class GenericResource {
    
    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@144.217.163.57:1521:XE";
    
    
    static final String USER = "SALES";
    static final String PASS = "cegepgim";
    
    JSONArray mainJsonAraay = new JSONArray();
    JSONObject singleObj = new JSONObject();
    
    Connection conn = null;
    Statement stmt = null;
    Statement Hstmt = null;
         
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of calls.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("clientList")
    @Produces("application/json")
    public String clientList() {
        
        
           
        return "";
    }
    
    
    public Connection getCon()
    {
         Connection conn = null;
        try
        {
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            
            //Open Connection
           
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      return conn;
    }
    
    
    
    
}
