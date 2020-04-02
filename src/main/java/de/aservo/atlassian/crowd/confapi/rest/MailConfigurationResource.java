package de.aservo.atlassian.crowd.confapi.rest;

import com.atlassian.crowd.manager.mail.MailConfiguration;
import com.atlassian.crowd.manager.mail.MailManager;
import com.atlassian.crowd.util.mail.SMTPServer;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import de.aservo.atlassian.confapi.constants.ConfAPI;
import de.aservo.atlassian.confapi.model.ErrorCollection;
import de.aservo.atlassian.confapi.model.MailServerSmtpBean;
import de.aservo.atlassian.crowd.confapi.helper.CrowdWebAuthenticationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path(ConfAPI.MAIL_SERVER)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class MailConfigurationResource {

    private static final Logger log = LoggerFactory.getLogger(MailConfigurationResource.class);

    @ComponentImport
    private final MailManager mailManager;

    private final CrowdWebAuthenticationHelper crowdWebAuthenticationHelper;

    @Inject
    public MailConfigurationResource(
            final MailManager mailManager,
            final CrowdWebAuthenticationHelper crowdWebAuthenticationHelper) {

        this.mailManager = mailManager;
        this.crowdWebAuthenticationHelper = crowdWebAuthenticationHelper;
    }

    @GET
    @Path(ConfAPI.MAIL_SERVER_SMTP)
    public Response getMailServerSmtp() {
        crowdWebAuthenticationHelper.mustBeSysAdmin();

        final ErrorCollection errorCollection = new ErrorCollection();

        try {
            final MailConfiguration mailConfiguration = mailManager.getMailConfiguration();
            final MailServerSmtpBean mailConfigurationBean = new MailServerSmtpBean(
                    mailConfiguration.getServerAlertAddress(),
                    mailConfiguration.getSmtpServer().getFrom().toString(),
                    mailConfiguration.getSmtpServer().getPrefix(),
                    mailConfiguration.getSmtpServer().getHost()
            );
            return Response.ok(mailConfigurationBean).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            errorCollection.addErrorMessage(e.getMessage());
        }

        return Response.status(NOT_FOUND).entity(errorCollection).build();
    }

    @PUT
    @Path(ConfAPI.MAIL_SERVER_SMTP)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putMailServerSmtp(
            final MailServerSmtpBean bean) {

        crowdWebAuthenticationHelper.mustBeSysAdmin();

        final ErrorCollection errorCollection = new ErrorCollection();

        try {
            final MailConfiguration mailConfiguration = mailManager.getMailConfiguration();

            final MailConfiguration newMailConfig = MailConfiguration.builder()
                    .setServerAlertAddress(bean.getAdminContact())
                    .setSmtpServer(SMTPServer.builder()
                            .setFrom(new InternetAddress(bean.getFrom()))
                            .setPrefix(bean.getPrefix())
                            .setHost(bean.getHost())
                            .setPort(mailConfiguration.getSmtpServer().getPort())
                            .setUseSSL(mailConfiguration.getSmtpServer().getUseSSL())
                            .setJndiLocation(mailConfiguration.getSmtpServer().getJndiLocation())
                            .setJndiMailActive(mailConfiguration.getSmtpServer().isJndiMailActive())
                            .setUsername(mailConfiguration.getSmtpServer().getUsername())
                            .setPassword(mailConfiguration.getSmtpServer().getPassword())
                            .setTimeout(mailConfiguration.getSmtpServer().getTimeout())
                            .build())
                    .build();
            mailManager.saveConfiguration(newMailConfig);

            return getMailServerSmtp();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            errorCollection.addErrorMessage(e.getMessage());
        }

        return Response.status(BAD_REQUEST).entity(errorCollection).build();
    }

}
