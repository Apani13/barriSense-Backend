package com.barriSenseBack.barrisense_feedback_api.entity;

import java.util.List;


public class Neighborhood {

    private long id;
    private String name;
    private String district;
    private List<Long> cp;

    public Neighborhood(String name, long id, String district, List<Long> cp) {
        this.name = name;
        this.id = id;
        this.district = district;
        this.cp = cp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Long> getCp() {
        return cp;
    }

    public void setCp(List<Long> cp) {
        this.cp = cp;
    }
}
