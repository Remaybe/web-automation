import io.qameta.allure.Step;

public enum ProjectType {
    AEO_MOBILE {
        @Step("Opens case study's page of 'AEO Mobile' project")
        public CaseStudiesPage getStudiesPage(ProjectsPage projectsPage) {
            projectsPage
                    .filterByCmbbxValue(Comboboxes.ACCOUNTS, "AEO")
                    .filterByCmbbxValue(Comboboxes.TECHNOLOGIES, "Typescript")
                    .openProject();
            return projectsPage.openProject();
        }
    };

    public abstract CaseStudiesPage getStudiesPage(ProjectsPage projectsPage);
}
