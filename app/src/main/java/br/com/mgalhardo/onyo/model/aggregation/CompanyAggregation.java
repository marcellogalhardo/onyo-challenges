package br.com.mgalhardo.onyo.model.aggregation;

import java.util.ArrayList;
import java.util.List;

import br.com.mgalhardo.onyo.model.implementation.Category;
import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.model.implementation.Menu;
import br.com.mgalhardo.onyo.model.implementation.Parameters;
import br.com.mgalhardo.onyo.model.Model;

public class CompanyAggregation implements Model  {

    public static final String KEY = "CompanyAggregation";

    public Integer numericalId;
    public String name;
    public Parameters parameters;
    public List<Company> companies = new ArrayList<>();
    public List<Menu> menus = new ArrayList<>();
    public String id;
    public List<Category> categories = new ArrayList<>();

}
