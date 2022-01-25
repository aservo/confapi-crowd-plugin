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
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS).build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testGetIdApplicationLink() {
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testDeleteAllApplicationLinks() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?force=true").build();
        ClientResponse clientResponse = settingsResource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testDeleteIdApplicationLink() {
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = settingsResource.delete();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutAllApplicationLinks() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = settingsResource.put(ApplicationLinksBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutIdApplicationLink() {
        UUID applicationLinkUuid = createApplicationLinkGetId(ApplicationLinkBean.EXAMPLE_1);

        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "/" + applicationLinkUuid + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = settingsResource.put(ApplicationLinkBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testAddNewApplicationLink() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = settingsResource.post(ApplicationLinkBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    private UUID createApplicationLinkGetId(ApplicationLinkBean bean) {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.APPLICATION_LINKS + "?ignore-setup-errors=true").build();
        ClientResponse clientResponse = settingsResource.post(bean);

        return clientResponse.getEntity(ApplicationLinkBean.class).getUuid();
    }
}
