package it.de.aservo.confapi.crowd.rest;

import de.aservo.confapi.commons.model.LicensesBean;
import it.de.aservo.confapi.commons.rest.AbstractLicenseResourceFuncTest;
import org.junit.Ignore;
import org.junit.Test;

public class LicensesResourceFuncTest extends AbstractLicenseResourceFuncTest {

    @Override
    protected LicensesBean getExampleBean() {
        return LicensesBean.EXAMPLE_2_DEVELOPER_LICENSE;
    }

    @Test
    @Ignore("There is no set method to test")
    @Override
    public void testSetLicenses(){
        //NOSONAR: no test
    }

    @Test
    @Ignore("There is no set method to test")
    @Override
    public void testSetLicensesUnauthenticated(){
        //NOSONAR: no test
    }

    @Test
    @Ignore("There is no set method to test")
    @Override
    public void testSetLicensesUnauthorized(){
        //NOSONAR: no test
    }
}
