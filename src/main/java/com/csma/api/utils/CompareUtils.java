package com.csma.api.utils;

import com.csma.api.CaseStudiesData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompareUtils {
    public static boolean hasDuplicateIdentifiers(List<CaseStudiesData> list){
        Set<Integer> updList = new HashSet();
        for(CaseStudiesData value : list) {
            if(updList.contains(value.getId())) return true;
            else updList.add(value.getId());
        }
        return false;
    }

    public static boolean hasEmptyNames(List<CaseStudiesData> caseStudiesData){
        for (CaseStudiesData study : caseStudiesData){
            if(study.getName().equals("")) return true;
        }
        return false;
    }

    public static boolean containsOnlyValue(String value, List<String> list){
        for (String val : list) {
            if(!val.contains(value)) return false;
        }
        return true;
    }
}
