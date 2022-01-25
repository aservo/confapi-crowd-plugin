package it.de.aservo.confapi.crowd.rest;


import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import it.de.aservo.confapi.commons.rest.ResourceBuilder;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class MailServerResourceFuncTest {

    @Test
    public void testGetMailServerSmtp() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP).build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testSetMailServerSmtp() {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP).build();
        ClientResponse clientResponse = settingsResource.put(MailServerSmtpBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }
}
