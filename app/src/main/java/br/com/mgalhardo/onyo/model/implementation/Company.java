package br.com.mgalhardo.onyo.model.implementation;

import java.util.ArrayList;
import java.util.List;

import br.com.mgalhardo.onyo.model.Model;

public class Company implements Model {

    public static final String KEY = "Company";

    public Integer numericalId;
    public String geoLat;
    public List<Image> image = new ArrayList<>();
    public String address;
    public String id;
    public String displayName;
    public String name;
    public Parameters parameters;
    public String menu;
    public String geoLon;
    public String brand;

}
