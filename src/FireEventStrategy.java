import java.util.Random;

public class FireEventStrategy implements EventStrategy {
    private Random random;

    public FireEventStrategy() {
        this.random = new Random();
    }

    @Override
    public String execute(City city) {
        StringBuilder description = new StringBuilder("🔥 A FIRE broke out in the city!");

        if (!city.getBuildings().isEmpty()) {
            Building building = city.getBuildings().get(random.nextInt(city.getBuildings().size()));
            building.damage();
            description.append("\n").append(building.getName()).append(" has been damaged!");
            description.append("\nRepair cost: $").append(String.format("%.2f", building.getRepairCost()));
            city.updateBudget(-building.getRepairCost() * GameConstants.FIRE_REPAIR_COST_MULTIPLIER);
        } else {
            description.append("\nFortunately, no buildings were damaged.");
            city.updateBudget(-GameConstants.FIRE_BASE_COST);
        }
        city.updateStats(GameConstants.FIRE_HAPPINESS_PENALTY, GameConstants.FIRE_SAFETY_PENALTY, GameConstants.FIRE_ENVIRONMENT_PENALTY);

        return description.toString();
    }

    @Override
    public EventType getEventType() {
        return EventType.FIRE;
    }
}
