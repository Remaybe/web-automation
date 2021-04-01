import io.qameta.allure.Step;

public enum ProjectType {
    AEO {
        @Step("Opens case study's page of 'AEO / TO - BSA' project")
        public CaseStudiesPage getStudiesPage(ProjectsPage projectsPage) {
            projectsPage
                    .filterByCmbbxValue(Comboboxes.ACCOUNTS, "AEO")
                    .filterByCmbbxValue(Comboboxes.PROJECTS, "TO - BSA");
            return projectsPage.openProject();
        }
    };

    public abstract CaseStudiesPage getStudiesPage(ProjectsPage projectsPage);
}
