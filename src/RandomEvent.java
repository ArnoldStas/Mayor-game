import java.util.Random;

public class RandomEvent {

    private EventType type;
    private String description;
    private Random random;

    public RandomEvent() {
        this.random = new Random();
    }

    public void generateEvent(City city) {
        // 30% probability of an event occurring
        if (random.nextDouble() > 0.3) {
            type = EventType.NONE;
            return;
        }

        // Randomly select an event
        EventType[] events = {EventType.FIRE, EventType.PROTEST, EventType.ECONOMIC_BOOM,
                               EventType.NATURAL_DISASTER, EventType.FESTIVAL_REQUEST};
        type = events[random.nextInt(events.length)];

        applyEvent(city);
    }

    private void applyEvent(City city) {
        switch (type) {
            case FIRE:
                handleFire(city);
                break;
            case PROTEST:
                handleProtest(city);
                break;
            case ECONOMIC_BOOM:
                handleEconomicBoom(city);
                break;
            case NATURAL_DISASTER:
                handleNaturalDisaster(city);
                break;
            case FESTIVAL_REQUEST:
                handleFestivalRequest(city);
                break;
            case NONE:
                break;
        }
    }

    private void handleFire(City city) {
        description = "🔥 A FIRE broke out in the city!";

        if (!city.getBuildings().isEmpty()) {
            Building building = city.getBuildings().get(random.nextInt(city.getBuildings().size()));
            building.damage();
            description += "\n" + building.getName() + " has been damaged!";
            description += "\nRepair cost: $" + String.format("%.2f", building.getRepairCost());
            city.updateBudget(-building.getRepairCost() * 0.5); // Immediate fire damage cost
        } else {
            description += "\nFortunately, no buildings were damaged.";
            city.updateBudget(-1000); // Still some cost for firefighting
        }
        city.updateStats(-5, -3, -2);
    }

    private void handleProtest(City city) {
        description = "📢 Citizens are PROTESTING against current policies!";
        city.updateStats(-10, -5, 0);
        description += "\nHappiness decreased by 10, Safety decreased by 5.";
    }

    private void handleEconomicBoom(City city) {
        description = "💰 ECONOMIC BOOM! The city's economy is thriving!";
        double bonus = 5000 + (city.getPopulation() * 2);
        city.updateBudget(bonus);
        city.updateStats(5, 0, 0);
        description += "\nBudget increased by $" + String.format("%.2f", bonus);
        description += "\nHappiness increased by 5.";
    }

    private void handleNaturalDisaster(City city) {
        description = "⚠️ NATURAL DISASTER struck the city!";

        int damageCount = Math.min(3, city.getBuildings().size());
        for (int i = 0; i < damageCount; i++) {
            if (!city.getBuildings().isEmpty()) {
                Building building = city.getBuildings().get(random.nextInt(city.getBuildings().size()));
                building.damage();
            }
        }

        double cost = 3000 + (city.getPopulation() * 1.5);
        city.updateBudget(-cost);
        city.updateStats(-15, -10, -10);

        description += "\n" + damageCount + " buildings damaged!";
        description += "\nEmergency response cost: $" + String.format("%.2f", cost);
        description += "\nAll city stats significantly decreased.";
    }

    private void handleFestivalRequest(City city) {
        description = "🎉 Citizens are requesting a FESTIVAL to boost morale!";
        description += "\nCost: $3000";
        description += "\nWould boost happiness by 15.";
        description += "\n(This is automatically approved if you have the budget)";

        if (city.getBudget() >= 3000) {
            city.updateBudget(-3000);
            city.updateStats(15, 0, 0);
            description += "\n✓ Festival held successfully!";
        } else {
            description += "\n✗ Not enough budget! Citizens are disappointed.";
            city.updateStats(-5, 0, 0);
        }
    }

    public EventType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasEvent() {
        return type != EventType.NONE;
    }

    public void displayEvent() {
        if (hasEvent()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("🚨 RANDOM EVENT 🚨");
            System.out.println("=".repeat(50));
            System.out.println(description);
            System.out.println("=".repeat(50));
        }
    }
}
