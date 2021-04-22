package com.csma.utils;

import io.qameta.allure.Step;
import com.csma.page_objects.CaseStudiesPage;
import com.csma.page_objects.ProjectsPage;

public enum ProjectType {
    AEO {
        @Step("Opens case study's page of 'AEO / TO - BSA' project")
        public CaseStudiesPage getStudiesPage(ProjectsPage projectsPage) {
            projectsPage
                    .filterByCmbbxValue(Comboboxes.ACCOUNT, "AEO")
                    .filterByCmbbxValue(Comboboxes.PROJECT, "TO - BSA");
            return projectsPage.openProject();
        }
    };

    public abstract CaseStudiesPage getStudiesPage(ProjectsPage projectsPage);
}
