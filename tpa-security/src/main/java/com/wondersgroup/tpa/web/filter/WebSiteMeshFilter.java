package com.wondersgroup.tpa.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/main")
                .addExcludedPath("/main")
                .addExcludedPath("/login")
                .addExcludedPath("/static/*");
    }
}