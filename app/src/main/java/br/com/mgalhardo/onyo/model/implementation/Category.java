package br.com.mgalhardo.onyo.model.implementation;


import java.util.ArrayList;
import java.util.List;

import br.com.mgalhardo.onyo.model.Model;

public class Category implements Model {

    public Integer numericalId;
    public String name;
    public Object parent;
    public String menu;
    public String brand;
    public List<Image> image = new ArrayList<>();
    public String id;
    public Integer order;

}
