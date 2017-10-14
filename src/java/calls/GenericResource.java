/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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
    
    
    static final String USER = "sales";
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
    @Path("singleClient&{id}")
    @Produces("application/json")
    public String singleClient(@PathParam("id") int id) {
           int employee_id = id;
           
           try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt=conn.createStatement();
            String sql;
            sql="select * from client where noclient="+ employee_id;
         
            ResultSet rs = stmt.executeQuery(sql);
            
  
            boolean empty = true;
            while (rs.next()) {
                int fetched_id = rs.getInt("noclient");
                String fname=rs.getString("nameclient");
                String lname=rs.getString("notelephone");
                 singleObj.accumulate("Status", "OK");
                 singleObj.accumulate("Timestamp",  myTimeStamp());
                 singleObj.accumulate("NOCLIENT", fetched_id);
                 singleObj.accumulate("NAMECLIENT", fname);
                 singleObj.accumulate("NOTELEPHONE", lname);
                 empty = false;
             }
        
            if (empty) {
                 singleObj.accumulate("Status", "WARNING");
                 singleObj.accumulate("Timestamp",  myTimeStamp());
                 singleObj.accumulate("NOCLIENT", employee_id);
                 singleObj.accumulate("MESSAGE", "No information available");
            }
            
            rs.close();
            stmt.close();
            conn.close();
       
            
        } catch (SQLException se) {
            //se.printStackTrace();
             singleObj.accumulate("Status", "ERROR");
                 singleObj.accumulate("Timestamp",  myTimeStamp());
                 singleObj.accumulate("NOCLIENT", employee_id);
                 singleObj.accumulate("MESSAGE", "database connexion failed");
            
        } catch (Exception e){
            e.printStackTrace();
             String result = "test";
        } 
           
            String result = singleObj.toString();
           
           
           return result;
       }
       
      
    public int myTimeStamp(){
        int time_cust=(int)(new Date().getTime()/1000);
        return time_cust;
    }
    
    
}
