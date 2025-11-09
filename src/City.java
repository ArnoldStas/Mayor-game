import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private int population;
    private double budget;
    private int happiness;
    private int safety;
    private int environment;
    private int taxRate;
    private List<Building> buildings;
    private int currentTurn;

    public City(String name) {
        this.name = name;
        this.population = 1000;
        this.budget = 50000;
        this.happiness = 50;
        this.safety = 50;
        this.environment = 50;
        this.taxRate = 10;
        this.buildings = new ArrayList<>();
        this.currentTurn = 1;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void updateStats(int happinessChange, int safetyChange, int environmentChange) {
        this.happiness = clamp(this.happiness + happinessChange, 0, 100);
        this.safety = clamp(this.safety + safetyChange, 0, 100);
        this.environment = clamp(this.environment + environmentChange, 0, 100);
    }

    public void updateBudget(double amount) {
        this.budget += amount;
    }

    public void updatePopulation(int change) {
        this.population += change;
        if (this.population < 0) this.population = 0;
    }

    public void nextTurn() {
        currentTurn++;

        double taxRevenue = (population * taxRate * 0.1);
        budget += taxRevenue;

        for (Building building : buildings) {
            budget -= building.getMaintenanceCost();
        }

        if (happiness > 70) {
            updatePopulation((int)(population * 0.02));
        } else if (happiness < 30) {
            updatePopulation((int)(population * -0.02));
        }

        for (Building building : buildings) {
            if (!building.isDamaged()) {
                building.applyEffects(this);
            }
        }
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public String getName() { return name; }
    public int getPopulation() { return population; }
    public double getBudget() { return budget; }
    public int getHappiness() { return happiness; }
    public int getSafety() { return safety; }
    public int getEnvironment() { return environment; }
    public int getTaxRate() { return taxRate; }
    public List<Building> getBuildings() { return buildings; }
    public int getCurrentTurn() { return currentTurn; }

    public void setTaxRate(int taxRate) {
        this.taxRate = clamp(taxRate, 0, 50);
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setHappiness(int happiness) {
        this.happiness = clamp(happiness, 0, 100);
    }

    public void setSafety(int safety) {
        this.safety = clamp(safety, 0, 100);
    }

    public void setEnvironment(int environment) {
        this.environment = clamp(environment, 0, 100);
    }

    public void displayStatus() {
        System.out.println("\n=== " + name.toUpperCase() + " STATUS ===");
        System.out.println("Turn: " + currentTurn + "/20");
        System.out.println("Population: " + population);
        System.out.println("Budget: $" + String.format("%.2f", budget));
        System.out.println("Happiness: " + happiness + "/100");
        System.out.println("Safety: " + safety + "/100");
        System.out.println("Environment: " + environment + "/100");
        System.out.println("Tax Rate: " + taxRate + "%");
        System.out.println("Buildings: " + buildings.size());
    }
}
