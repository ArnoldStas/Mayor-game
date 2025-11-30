public class ProtestEventStrategy implements EventStrategy {

    @Override
    public String execute(City city) {
        city.updateStats(GameConstants.PROTEST_HAPPINESS_PENALTY, GameConstants.PROTEST_SAFETY_PENALTY, 0);
        return "📢 Citizens are PROTESTING against current policies!\n" +
               "Happiness decreased by " + Math.abs(GameConstants.PROTEST_HAPPINESS_PENALTY) +
               ", Safety decreased by " + Math.abs(GameConstants.PROTEST_SAFETY_PENALTY) + ".";
    }

    @Override
    public EventType getEventType() {
        return EventType.PROTEST;
    }
}
