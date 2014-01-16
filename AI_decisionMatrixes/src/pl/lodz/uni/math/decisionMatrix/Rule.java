package pl.lodz.uni.math.decisionMatrix;

import java.util.ArrayList;

public class Rule implements Comparable{
    private int id;
    private int numberOfDoors;
    private int enginePower;
    private String color;
    private DecisionAttribute brand;

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public DecisionAttribute getBrand() {
        return brand;
    }

    public void setBrand(DecisionAttribute brand) {
        this.brand = brand;
    }

    public Rule(int id, int numberOfDoors, int enginePower, String color,
            DecisionAttribute brand) {
        this.id = id;
        this.numberOfDoors = numberOfDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.brand = brand;
    }

    static public ArrayList<Rule> lowerApproximation(ArrayList<Rule> rules) {

        ArrayList<Rule> positiveRegion = new ArrayList<Rule>();

        for (Rule rule : rules) {
            boolean deterministic = true;
            ArrayList<Rule> candidates = new ArrayList<Rule>();
            for (Rule innerRule : rules) {
                if (!rule.equals(innerRule)
                        && !positiveRegion.contains(innerRule)) {
                    if (rule.getColor().equals(innerRule.getColor())
                            && rule.getEnginePower() == innerRule
                                    .getEnginePower()
                            && rule.getNumberOfDoors() == innerRule
                                    .getNumberOfDoors()) {
                        if (!rule.getBrand().equals(innerRule.getBrand())) {
                            deterministic = false;
                            break;
                        }
                        if (deterministic) {
                            candidates.add(innerRule);
                        }
                    }
                }

            }
            if (!positiveRegion.contains(rule)) {
                candidates.add(rule);
            }

            if (deterministic) {
                positiveRegion.addAll(candidates);
            }

        }
        return positiveRegion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
    
        return "id: "+id+" color:"+color+" enginePower:"+enginePower+" numberOfDoors:"+numberOfDoors+" brand:"+ brand;
    }

    @Override
    public int compareTo(Object arg0) {
        Rule arg=(Rule)arg0;
        if (arg.getId()>this.id) {
            return -1;
        } else if(arg.getId()<this.id){
            return 1;
        } 
        else {
            return 0;
        }
        
    }
}
