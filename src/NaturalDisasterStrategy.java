import java.util.Random;

public class NaturalDisasterStrategy implements EventStrategy {
    private Random random;

    public NaturalDisasterStrategy() {
        this.random = new Random();
    }

    @Override
    public String execute(City city) {
        int damageCount = Math.min(GameConstants.NATURAL_DISASTER_MAX_DAMAGE_COUNT, city.getBuildings().size());
        for (int i = 0; i < damageCount; i++) {
            if (!city.getBuildings().isEmpty()) {
                Building building = city.getBuildings().get(random.nextInt(city.getBuildings().size()));
                building.damage();
            }
        }

        double cost = GameConstants.NATURAL_DISASTER_BASE_COST + (city.getPopulation() * GameConstants.NATURAL_DISASTER_POPULATION_MULTIPLIER);
        city.updateBudget(-cost);
        city.updateStats(GameConstants.NATURAL_DISASTER_HAPPINESS_PENALTY,
                        GameConstants.NATURAL_DISASTER_SAFETY_PENALTY,
                        GameConstants.NATURAL_DISASTER_ENVIRONMENT_PENALTY);

        return "⚠️ NATURAL DISASTER struck the city!\n" +
               damageCount + " buildings damaged!\n" +
               "Emergency response cost: $" + String.format("%.2f", cost) + "\n" +
               "All city stats significantly decreased.";
    }

    @Override
    public EventType getEventType() {
        return EventType.NATURAL_DISASTER;
    }
}
