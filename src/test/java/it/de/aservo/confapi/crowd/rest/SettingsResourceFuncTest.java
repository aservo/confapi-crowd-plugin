package it.de.aservo.confapi.crowd.rest;


import de.aservo.confapi.commons.model.SettingsBean;
import it.de.aservo.confapi.commons.rest.AbstractSettingsResourceFuncTest;

import java.net.URI;


public class SettingsResourceFuncTest extends AbstractSettingsResourceFuncTest {

    @Override
    protected SettingsBean getExampleBean() {
        SettingsBean settingsBean = new SettingsBean();
        settingsBean.setTitle("Example");
        settingsBean.setBaseUrl(URI.create("https://example.com"));
        return settingsBean;
    }
}
