public abstract class Building {
    protected String name;
    protected double buildCost;
    protected double maintenanceCost;
    protected boolean damaged;
    protected double repairCost;

    public Building(String name, double buildCost, double maintenanceCost) {
        this.name = name;
        this.buildCost = buildCost;
        this.maintenanceCost = maintenanceCost;
        this.damaged = false;
        this.repairCost = buildCost * GameConstants.REPAIR_COST_MULTIPLIER;
    }

    public abstract void applyEffects(City city);

    public void damage() {
        this.damaged = true;
    }

    public void repair() {
        this.damaged = false;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public String getName() {
        return name;
    }

    public double getBuildCost() {
        return buildCost;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public double getRepairCost() {
        return repairCost;
    }

    @Override
    public String toString() {
        return name + (damaged ? " (DAMAGED)" : "");
    }
}
