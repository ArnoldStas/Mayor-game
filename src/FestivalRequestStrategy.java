public class FestivalRequestStrategy implements EventStrategy {

    @Override
    public String execute(City city) {
        StringBuilder description = new StringBuilder(
            "🎉 Citizens are requesting a FESTIVAL to boost morale!\n" +
            "Cost: $" + String.format("%.0f", GameConstants.FESTIVAL_COST) + "\n" +
            "Would boost happiness by " + GameConstants.FESTIVAL_HAPPINESS_BOOST + ".\n" +
            "(This is automatically approved if you have the budget)"
        );

        if (city.getBudget() >= GameConstants.FESTIVAL_COST) {
            city.updateBudget(-GameConstants.FESTIVAL_COST);
            city.updateStats(GameConstants.FESTIVAL_HAPPINESS_BOOST, 0, 0);
            description.append("\n✓ Festival held successfully!");
        } else {
            description.append("\n✗ Not enough budget! Citizens are disappointed.");
            city.updateStats(GameConstants.FESTIVAL_DISAPPOINTMENT_PENALTY, 0, 0);
        }

        return description.toString();
    }

    @Override
    public EventType getEventType() {
        return EventType.FESTIVAL_REQUEST;
    }
}
