package it.de.aservo.confapi.crowd.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.ApplicationLinkBean;
import de.aservo.confapi.commons.model.ApplicationLinksBean;
import it.de.aservo.confapi.commons.rest.ResourceBuilder;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ApplicationLinksResourceFuncTest {

    @Test
    public void testGetAllApplicationLinks() {
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ApplicationLinksBean applicationLinksBean = clientResponse.getEntity(ApplicationLinksBean.class);
        assertEquals(1, applicationLinksBean.getApplicationLinks().size());
    }

    @Test
    public void testGetIdApplicationLink() {
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals(applicationLinkUuid, clientResponse.getEntity(ApplicationLinkBean.class).getUuid());
    }

    @Test
    public void testDeleteAllApplicationLinks() {
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?force=true").build();
        ClientResponse clientResponse = resource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ClientResponse clientGetResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientGetResponse.getStatusCode());
        ApplicationLinksBean applicationLinksBean = clientGetResponse.getEntity(ApplicationLinksBean.class);
        assertEquals(0, applicationLinksBean.getApplicationLinks().size());
    }

    @Test
    public void testDeleteIdApplicationLink() {
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = resource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ClientResponse clientGetResponse = resource.get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), clientGetResponse.getStatusCode());
    }

    @Test
    public void testPutAllApplicationLinks() {// TODO
        UUID applicationLinkUuidExampleOne = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);
        UUID applicationLinkUuidExampleTwo = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = resource.put(ApplicationLinksBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutIdApplicationLink() {// TODO
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ApplicationLinkBean exampleBean = ApplicationLinkBean.EXAMPLE_1;
        exampleBean.setUsername("new-user");
        ClientResponse clientResponse = resource.put(exampleBean);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        ClientResponse clientGetResponse = resource.get();
        assertEquals(clientGetResponse.getEntity(ApplicationLinkBean.class).getName(),exampleBean.getUsername());
    }

    @Test
    public void testAddNewApplicationLink() {
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals(applicationLinkUuid, clientResponse.getEntity(ApplicationLinkBean.class).getUuid());
    }

    private UUID createApplicationLinkGetId(ApplicationLinkBean bean) {
        Resource resource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = resource.post(bean);

        return clientResponse.getEntity(ApplicationLinkBean.class).getUuid();
    }
}
