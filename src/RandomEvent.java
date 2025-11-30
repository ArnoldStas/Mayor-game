import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class RandomEvent {

    private EventStrategy currentStrategy;
    private String description;
    private Random random;
    private Map<EventType, EventStrategy> strategies;

    public RandomEvent() {
        this.random = new Random();
        this.strategies = new HashMap<>();
        initializeStrategies();
    }

    private void initializeStrategies() {
        strategies.put(EventType.FIRE, new FireEventStrategy());
        strategies.put(EventType.PROTEST, new ProtestEventStrategy());
        strategies.put(EventType.ECONOMIC_BOOM, new EconomicBoomStrategy());
        strategies.put(EventType.NATURAL_DISASTER, new NaturalDisasterStrategy());
        strategies.put(EventType.FESTIVAL_REQUEST, new FestivalRequestStrategy());
    }

    public void generateEvent(City city) {
        if (random.nextDouble() > GameConstants.EVENT_PROBABILITY) {
            currentStrategy = null;
            description = null;
            return;
        }

        EventType[] events = {
            EventType.FIRE,
            EventType.PROTEST,
            EventType.ECONOMIC_BOOM,
            EventType.NATURAL_DISASTER,
            EventType.FESTIVAL_REQUEST
        };

        EventType selectedType = events[random.nextInt(events.length)];
        currentStrategy = strategies.get(selectedType);

        if (currentStrategy != null) {
            description = currentStrategy.execute(city);
        }
    }

    public EventType getType() {
        return currentStrategy != null ? currentStrategy.getEventType() : EventType.NONE;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasEvent() {
        return currentStrategy != null;
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
