package com.chuisha.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/manager/*", "/main")
                .addDecoratorPath("/student/*","/main-student")
                .addExcludedPath("/main")
                .addExcludedPath("/main-student")
                .addExcludedPath("/login")
                .addExcludedPath("/static/*");
    }
}