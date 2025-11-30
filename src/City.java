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
        this.population = GameConstants.INITIAL_POPULATION;
        this.budget = GameConstants.INITIAL_BUDGET;
        this.happiness = GameConstants.INITIAL_HAPPINESS;
        this.safety = GameConstants.INITIAL_SAFETY;
        this.environment = GameConstants.INITIAL_ENVIRONMENT;
        this.taxRate = GameConstants.INITIAL_TAX_RATE;
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
        this.happiness = clamp(this.happiness + happinessChange, GameConstants.MIN_STAT_VALUE, GameConstants.MAX_STAT_VALUE);
        this.safety = clamp(this.safety + safetyChange, GameConstants.MIN_STAT_VALUE, GameConstants.MAX_STAT_VALUE);
        this.environment = clamp(this.environment + environmentChange, GameConstants.MIN_STAT_VALUE, GameConstants.MAX_STAT_VALUE);
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

        double taxRevenue = (population * taxRate * GameConstants.TAX_REVENUE_MULTIPLIER);
        budget += taxRevenue;

        for (Building building : buildings) {
            budget -= building.getMaintenanceCost();
        }

        if (happiness > GameConstants.HAPPINESS_THRESHOLD_FOR_GROWTH) {
            updatePopulation((int)(population * GameConstants.POPULATION_GROWTH_RATE));
        } else if (happiness < GameConstants.HAPPINESS_THRESHOLD_FOR_DECLINE) {
            updatePopulation((int)(population * -GameConstants.POPULATION_DECLINE_RATE));
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
        this.taxRate = clamp(taxRate, GameConstants.MIN_TAX_RATE, GameConstants.MAX_TAX_RATE);
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setHappiness(int happiness) {
        this.happiness = clamp(happiness, GameConstants.MIN_STAT_VALUE, GameConstants.MAX_STAT_VALUE);
    }

    public void setSafety(int safety) {
        this.safety = clamp(safety, GameConstants.MIN_STAT_VALUE, GameConstants.MAX_STAT_VALUE);
    }

    public void setEnvironment(int environment) {
        this.environment = clamp(environment, GameConstants.MIN_STAT_VALUE, GameConstants.MAX_STAT_VALUE);
    }

    public void displayStatus() {
        System.out.println("\n=== " + name.toUpperCase() + " STATUS ===");
        System.out.println("Turn: " + currentTurn + "/" + GameConstants.MAX_TURNS);
        System.out.println("Population: " + population);
        System.out.println("Budget: $" + String.format("%.2f", budget));
        System.out.println("Happiness: " + happiness + "/100");
        System.out.println("Safety: " + safety + "/100");
        System.out.println("Environment: " + environment + "/100");
        System.out.println("Tax Rate: " + taxRate + "%");
        System.out.println("Buildings: " + buildings.size());
    }
}
