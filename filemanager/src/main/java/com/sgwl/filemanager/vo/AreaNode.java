package com.sgwl.filemanager.vo;

import lombok.Data;

import java.util.List;

@Data
public class AreaNode {
    private Long  codeid;
    private Long parentid;
    private String cityName;
    private List<AreaNode> children;


    public AreaNode(Long codeid, Long parentid, String cityName, List<AreaNode> children) {
        this.codeid = codeid;
        this.parentid = parentid;
        this.cityName = cityName;
        this.children = children;
    }
}
