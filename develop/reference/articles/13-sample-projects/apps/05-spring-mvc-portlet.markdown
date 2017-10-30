# Spring MVC Portlet [](id=spring-mvc-portlet)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Spring MVC portlet provides a way to add various different fields into the
database and display them in a table. This project is a Spring MVC based portlet
WAR that implements the same functionality as the `service-builder/foo-web`
sample project. It manages JSP pages for display, uses a Spring-annotated
portlet class, and invokes the `service-builder/foo-api` module to call
services.

![Figure 1: Click *Add* and fill out the sample fields to generate a custom entry in the portlet's table.](../../../images/spring-mvc-portlet.png)

Unlike the `service-builder/foo-web` module, Spring MVC portlets must be
delivered as portlet WAR projects. This project builds to a WAR file but
leverages all of the Liferay Workspace tools and Gradle to build the WAR. You
must build and deploy the `service-builder/foo-api` and
`service-builder/foo-service` modules for this sample to work properly. For more
information on using Spring MVC portlets in @product@, visit the
[Spring MVC](/develop/tutorials/-/knowledge_base/7-0/spring-mvc) tutorial.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample demonstrates a @product@ portlet built using the
[Spring Web MVC framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html).

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

You can easily modify this sample by customizing its
`SpringMVCPortletViewController` Java class or any of its JSPs stored in the
`src/main/webapp/WEB-INF/jsp` folder. For more information on customizing this
sample, see the Javadoc listed in this sample's `SpringMVCPortletViewController`
Java class.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/springmvc-portlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/wars/springmvc-portlet)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/apps/springmvc-portlet)
