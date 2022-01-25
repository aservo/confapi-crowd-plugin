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
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS + "/" + ConfAPI.SETTINGS_BRANDING + "/" + ConfAPI.SETTINGS_BRANDING_LOGIN_PAGE).build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testGetSettings()  {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS).build();
        ClientResponse clientResponse = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutLoginPage () {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS + "/" + ConfAPI.SETTINGS_BRANDING + "/" + ConfAPI.SETTINGS_BRANDING_LOGIN_PAGE).build();
        ClientResponse clientResponse = settingsResource.put(SettingsBrandingLoginPageBean.EXAMPLE_2);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }

    @Test
    public void testPutSettings () {
        Resource settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS).build();
        ClientResponse clientResponse = settingsResource.put(SettingsBean.EXAMPLE_1);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatusCode());
    }
}
