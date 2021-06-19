package com.example.veles_app;

public class State {

    private String name; // название
    private String capital;  // столица
    private String cost; //цена
    private int flagResource; // ресурс флага

    public State(String name, String capital,String cost, int flag){

        this.name=name;
        this.capital=capital;
        this.cost=cost;
        this.flagResource=flag;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

}
