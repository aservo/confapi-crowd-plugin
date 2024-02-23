package it.de.aservo.confapi.crowd.rest;


import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.SettingsBean;
import de.aservo.confapi.crowd.model.SettingsBrandingLoginPageBean;
import it.de.aservo.confapi.commons.rest.ResourceBuilder;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;


public class SettingsBrandingResourceFuncTest {

    @Test
    public void testGetLoginPage() {
        Resource resource = ResourceBuilder.builder(ConfAPI.SETTINGS + "/" + ConfAPI.SETTINGS_BRANDING + "/" + ConfAPI.SETTINGS_BRANDING_LOGIN_PAGE).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals(SettingsBrandingLoginPageBean.EXAMPLE_2, clientResponse.getEntity(SettingsBrandingLoginPageBean.class));
    }

    @Test
    public void testGetSettings()  {
        Resource resource = ResourceBuilder.builder(ConfAPI.SETTINGS).build();
        ClientResponse clientResponse = resource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
        assertEquals(SettingsBean.EXAMPLE_1.getBaseUrl(), clientResponse.getEntity(SettingsBean.class).getBaseUrl());
        assertEquals(SettingsBean.EXAMPLE_1.getTitle(), clientResponse.getEntity(SettingsBean.class).getTitle());
    }

    @Test
    public void testPutLoginPage () { // TODO
        Resource resource = ResourceBuilder.builder(ConfAPI.SETTINGS + "/" + ConfAPI.SETTINGS_BRANDING + "/" + ConfAPI.SETTINGS_BRANDING_LOGIN_PAGE).build();
        ClientResponse clientResponse = resource.put(SettingsBrandingLoginPageBean.EXAMPLE_2);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutSettings () { // TODO
        Resource resource = ResourceBuilder.builder(ConfAPI.SETTINGS).build();
        ClientResponse clientResponse = resource.put(SettingsBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }
}
