
public enum ProjectType {
    AEO_MOBILE {
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
