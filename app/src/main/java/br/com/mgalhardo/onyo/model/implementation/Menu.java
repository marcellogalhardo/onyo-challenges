package br.com.mgalhardo.onyo.model.implementation;

import java.util.ArrayList;
import java.util.List;

import br.com.mgalhardo.onyo.model.Model;

public class Menu implements Model {

    public Integer numericalId;
    public String id;
    public List<Object> categories = new ArrayList<>();

}
