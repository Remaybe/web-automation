import io.qameta.allure.Step;

public enum ProjectType {
    SUNFLOWER {
        @Step("Opens case study's page of 'Sunflower' project")
        public CaseStudiesPage getStudiesPage(ProjectsPage projectsPage) {
            projectsPage
                    .filterByCmbbxValue(Comboboxes.AREAS, "Fraud Detection");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return projectsPage.openProject();
        }
    };

    public abstract CaseStudiesPage getStudiesPage(ProjectsPage projectsPage);
}
