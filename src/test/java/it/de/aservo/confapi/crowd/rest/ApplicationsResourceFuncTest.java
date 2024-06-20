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
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ApplicationsBean response = clientResponse.getEntity(ApplicationsBean.class);
        assertEquals(4, response.getApplications().size());
    }

    @Test
    public void testGetIdApplication() {
        Long applicationId = createApplicationGetId(createApplicationBeanWithName("ExampleBean"));

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals(applicationId, clientResponse.getEntity(ApplicationBean.class).getId());
    }

    @Test
    public void testDeleteAllApplications() {
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/?force=true").build();
        ClientResponse clientResponse = resource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ClientResponse clientGetResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientGetResponse.getStatusCode());
        ApplicationsBean response = clientGetResponse.getEntity(ApplicationsBean.class);
        assertEquals(2, response.getApplications().size());
    }

    @Test
    public void testDeleteIdApplication() {
        Long applicationId = createApplicationGetId(createApplicationBeanWithName("ExampleBean2"));

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = resource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ClientResponse clientGetResponse = resource.get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), clientGetResponse.getStatusCode());
    }

    @Test
    public void testDeleteIdApplicationWithError() {
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/1").build();
        ClientResponse clientResponse = resource.delete();
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutAllApplications() {// TODO
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        createApplicationBeanWithName("Example1");
        createApplicationBeanWithName("Example2");
        ClientResponse clientResponse = resource.put(new ApplicationsBean((Collections.singletonList(ApplicationBean.EXAMPLE_1))));
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutIdApplication() { // TODO
        Long applicationId = createApplicationGetId(createApplicationBeanWithName("testPutExample"));

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = resource.put(ApplicationBean.EXAMPLE_2);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testAddNewApplication() {
        Long applicationId = createApplicationGetId(ApplicationBean.EXAMPLE_1);

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS + "/" + applicationId).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals( applicationId, clientResponse.getEntity(ApplicationBean.class).getId());
    }

    private Long createApplicationGetId(ApplicationBean bean) {
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATIONS).build();
        ClientResponse clientResponse = resource.post(bean);

        return clientResponse.getEntity(ApplicationBean.class).getId();
    }

    private  ApplicationBean createApplicationBeanWithName (String name) {
        ApplicationBean applicationBean = new ApplicationBean();
        applicationBean.setName(name);
        applicationBean.setDescription(name);
        applicationBean.setActive(true);
        applicationBean.setPassword("3x4mpl3");
        applicationBean.setType(ApplicationBean.ApplicationType.GENERIC);

        return  applicationBean;
    }
}
