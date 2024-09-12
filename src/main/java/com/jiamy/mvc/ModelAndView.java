package com.jiamy.mvc;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    Map<String, Object> model;

    String view;

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public ModelAndView setView(String view) {
        this.view = view;
        return this;
    }

    public ModelAndView() {
        this.model = new HashMap<>();
        this.view = "/";
    }

    public ModelAndView addModel(String name, Object o) {
        this.model.put(name,o);
        return this;
    }
}
