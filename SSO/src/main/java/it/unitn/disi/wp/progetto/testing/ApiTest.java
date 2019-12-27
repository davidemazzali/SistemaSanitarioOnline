package it.unitn.disi.wp.progetto.testing;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("topsecret")
public class ApiTest {
    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLanguages(@QueryParam("name") String name, @QueryParam("surname") String surname) {
        System.out.println("parametri arrivati: " + name + "  " + surname);
        Gson gson = new Gson();
        TestObject to = new TestObject(name, surname);
        String jsonInString = gson.toJson(to);
        return jsonInString;
    }

    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String handlePost(@QueryParam("name") String name, @QueryParam("surname") String surname) {
        System.out.println("parametri arrivati: " + name + "  " + surname);
        Gson gson = new Gson();
        TestObject to = new TestObject(name, surname);
        String jsonInString = gson.toJson(to);
        return jsonInString;
    }
    */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void handlePost(String xd){
        Gson g = new Gson();
        /**
         * trasforma un json in string
         */
        //xd deve essere nel formato "value"
        String p = g.fromJson(xd, String.class);
    }


    public class TestObject {
        private String name;
        private String surname;
        public TestObject(String name, String surname){
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return this.name;
        }

        public String getSurname(){
            return this.surname;
        }

    }
}
