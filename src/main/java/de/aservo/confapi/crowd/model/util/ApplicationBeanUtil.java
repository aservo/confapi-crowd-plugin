package de.aservo.confapi.crowd.model.util;

import com.atlassian.crowd.embedded.api.PasswordCredential;
import com.atlassian.crowd.model.application.Application;
import com.atlassian.crowd.model.application.ApplicationType;
import com.atlassian.crowd.model.application.ImmutableApplication;
import com.atlassian.crowd.model.application.RemoteAddress;
import de.aservo.confapi.crowd.model.ApplicationBean;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationBeanUtil {

    public static ApplicationBean toApplicationBean(
            final Application application) {

        final ApplicationBean applicationBean = new ApplicationBean();

        applicationBean.setId(application.getId());
        applicationBean.setName(application.getName());
        applicationBean.setDescription(application.getDescription());
        applicationBean.setActive(application.isActive());
        applicationBean.setType(toApplicationBeanType(application.getType()));
        applicationBean.setRemoteAddresses(toStringCollection(application.getRemoteAddresses()));

        return applicationBean;
    }

    public static Application toApplication(
            final ApplicationBean applicationBean) {

        // don't set id from request data
        return ImmutableApplication.builder(applicationBean.getName(), toApplicationType(applicationBean.getType()))
                .setDescription(applicationBean.getDescription())
                .setActive(applicationBean.getActive())
                .setPasswordCredential(PasswordCredential.unencrypted(applicationBean.getPassword()))
                .setRemoteAddresses(toAddressSet(applicationBean.getRemoteAddresses()))
                .build();
    }

    @Nullable
    public static ApplicationBean.ApplicationType toApplicationBeanType(
            final ApplicationType applicationType) {

        if (ApplicationType.GENERIC_APPLICATION.equals(applicationType)) {
            return ApplicationBean.ApplicationType.GENERIC;
        } else if (ApplicationType.PLUGIN.equals(applicationType)) {
            return ApplicationBean.ApplicationType.PLUGIN;
        } else if (ApplicationType.CROWD.equals(applicationType)) {
            return ApplicationBean.ApplicationType.CROWD;
        } else if (ApplicationType.JIRA.equals(applicationType)) {
            return ApplicationBean.ApplicationType.JIRA;
        } else if (ApplicationType.CONFLUENCE.equals(applicationType)) {
            return ApplicationBean.ApplicationType.CONFLUENCE;
        } else if (ApplicationType.STASH.equals(applicationType)) {
            return ApplicationBean.ApplicationType.BITBUCKET;
        } else if (ApplicationType.FISHEYE.equals(applicationType)) {
            return ApplicationBean.ApplicationType.FISHEYE;
        } else if (ApplicationType.CRUCIBLE.equals(applicationType)) {
            return ApplicationBean.ApplicationType.CRUCIBLE;
        } else if (ApplicationType.BAMBOO.equals(applicationType)) {
            return ApplicationBean.ApplicationType.BAMBOO;
        }

        return null;
    }

    @Nullable
    public static ApplicationType toApplicationType(
            final ApplicationBean.ApplicationType applicationBeanType) {

        if (ApplicationBean.ApplicationType.GENERIC.equals(applicationBeanType)) {
            return ApplicationType.GENERIC_APPLICATION;
        } else if (ApplicationBean.ApplicationType.PLUGIN.equals(applicationBeanType)) {
            return ApplicationType.PLUGIN;
        } else if (ApplicationBean.ApplicationType.CROWD.equals(applicationBeanType)) {
            return ApplicationType.CROWD;
        } else if (ApplicationBean.ApplicationType.JIRA.equals(applicationBeanType)) {
            return ApplicationType.JIRA;
        } else if (ApplicationBean.ApplicationType.CONFLUENCE.equals(applicationBeanType)) {
            return ApplicationType.CONFLUENCE;
        } else if (ApplicationBean.ApplicationType.BITBUCKET.equals(applicationBeanType)) {
            return ApplicationType.STASH;
        } else if (ApplicationBean.ApplicationType.FISHEYE.equals(applicationBeanType)) {
            return ApplicationType.FISHEYE;
        } else if (ApplicationBean.ApplicationType.CRUCIBLE.equals(applicationBeanType)) {
            return ApplicationType.CRUCIBLE;
        } else if (ApplicationBean.ApplicationType.BAMBOO.equals(applicationBeanType)) {
            return ApplicationType.BAMBOO;
        }

        return null;
    }

    @NotNull
    public static Set<RemoteAddress> toAddressSet(
            @Nullable final Collection<String> remoteAddresses) {

        if (remoteAddresses == null) {
            return new HashSet<>();
        }

        return remoteAddresses.stream()
                .map(RemoteAddress::new)
                .collect(Collectors.toSet());
    }

    @NotNull
    public static Collection<String> toStringCollection(
            @NotNull final Set<RemoteAddress> remoteAddresses) {

        return remoteAddresses.stream()
                .map(RemoteAddress::getAddress)
                .sorted()
                .collect(Collectors.toList());
    }

    private ApplicationBeanUtil() {
    }

}
