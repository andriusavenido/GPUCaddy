package dev.avenido.CaddyAPI.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

/**
 * Request DTO
 *
 * Site: Canada Computers, Memory Express
 * filter:
 */
public class GPURequest {

    private String site;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HashMap<String, String> filter; //model: ,


    public String getSite() {return site;}
    public void setSite(String site) {this.site = site;}
    public HashMap<String, String> getFilter() {return filter;}
    public void setFilter(HashMap<String,String> filter) {this.filter = filter;}

}
