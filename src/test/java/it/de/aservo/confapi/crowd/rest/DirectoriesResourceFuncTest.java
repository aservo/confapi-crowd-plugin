package it.de.aservo.confapi.crowd.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.DirectoriesBean;
import it.de.aservo.confapi.commons.rest.ResourceBuilder;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class DirectoriesResourceFuncTest {

    @Test
    public void testGetAllDirectories() {
        Resource resource = ResourceBuilder.builder(ConfAPI.DIRECTORIES).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        DirectoriesBean directoriesBean = clientResponse.getEntity(DirectoriesBean.class);
        assertEquals(1, directoriesBean.getDirectories().size());
    }

    @Test
    public void testGetIdDirectory() {
        //The ID need to be hardcoded here because there is no Add method to generate and catch the ID for a test directory
        Resource resource = ResourceBuilder.builder(ConfAPI.DIRECTORIES + "/131073").build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }
}
