package it.de.aservo.confapi.crowd.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.crowd.model.ApplicationBean;
import de.aservo.confapi.crowd.model.ApplicationsBean;
import it.de.aservo.confapi.commons.rest.ResourceBuilder;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ApplicationsResourceFuncTest {

    @Test
    public void testGetAllApplications() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testGetIdApplication() {
        Long applicationId = createApplicationGetId(createApplicationBean("testGetExample"));

        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testDeleteAllApplications() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/?force=true").build();
        createApplicationGetId(createApplicationBean("testDeleteAllExample1"));
        createApplicationGetId(createApplicationBean("testDeleteAllExample2"));

        ClientResponse clientResponse = settingsResource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testDeleteIdApplication() {
        Long applicationId = createApplicationGetId(createApplicationBean("testDeleteExample"));

        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = settingsResource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testDeleteIdApplicationWithError() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/1").build();
        ClientResponse clientResponse = settingsResource.delete();
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutAllApplications() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        createApplicationBean("app_name");
        ClientResponse clientResponse = settingsResource.put(new ApplicationsBean((Collections.singletonList(ApplicationBean.EXAMPLE_1))));
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutIdApplication() {
        Long applicationId = createApplicationGetId(createApplicationBean("testPutExample"));

        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = settingsResource.put(ApplicationBean.EXAMPLE_2);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testAddNewApplication() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        ClientResponse clientResponse = settingsResource.post(ApplicationBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    private Long createApplicationGetId(ApplicationBean bean) {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        ClientResponse clientResponse = settingsResource.post(bean);

        return clientResponse.getEntity(ApplicationBean.class).getId();
    }

    private  ApplicationBean createApplicationBean (String name) {
        ApplicationBean applicationBean = new ApplicationBean();
        applicationBean.setName(name);
        applicationBean.setDescription(name);
        applicationBean.setActive(true);
        applicationBean.setPassword("3x4mpl3");
        applicationBean.setType(ApplicationBean.ApplicationType.GENERIC);

        return  applicationBean;
    }
}
