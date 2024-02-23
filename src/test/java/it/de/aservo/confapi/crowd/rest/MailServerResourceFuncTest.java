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
        Resource resource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testSetMailServerSmtp() {
        Resource resource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP).build();
        ClientResponse clientResponse = resource.put(MailServerSmtpBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals(MailServerSmtpBean.EXAMPLE_1.getAdminContact(), clientResponse.getEntity(MailServerSmtpBean.class).getAdminContact());
        assertEquals(MailServerSmtpBean.EXAMPLE_1.getPrefix(), clientResponse.getEntity(MailServerSmtpBean.class).getPrefix());
        assertEquals(MailServerSmtpBean.EXAMPLE_1.getFrom(), clientResponse.getEntity(MailServerSmtpBean.class).getFrom());
        assertEquals(MailServerSmtpBean.EXAMPLE_1.getUseTls(), clientResponse.getEntity(MailServerSmtpBean.class).getUseTls());
    }
}
