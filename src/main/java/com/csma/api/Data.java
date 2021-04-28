package com.csma.api;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {

    @JsonProperty("caseStudies")
    private List<CaseStudiesData> caseStudiesData;

    public List<CaseStudiesData> getCaseStudiesData() {
        return caseStudiesData;
    }

    public void setCaseStudiesData(List<CaseStudiesData> caseStudiesData) {
        this.caseStudiesData = caseStudiesData;
    }
}
