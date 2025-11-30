public class EconomicBoomStrategy implements EventStrategy {

    @Override
    public String execute(City city) {
        double bonus = GameConstants.ECONOMIC_BOOM_BASE_BONUS + (city.getPopulation() * GameConstants.ECONOMIC_BOOM_POPULATION_MULTIPLIER);
        city.updateBudget(bonus);
        city.updateStats(GameConstants.ECONOMIC_BOOM_HAPPINESS_BOOST, 0, 0);

        return "💰 ECONOMIC BOOM! The city's economy is thriving!\n" +
               "Budget increased by $" + String.format("%.2f", bonus) + "\n" +
               "Happiness increased by " + GameConstants.ECONOMIC_BOOM_HAPPINESS_BOOST + ".";
    }

    @Override
    public EventType getEventType() {
        return EventType.ECONOMIC_BOOM;
    }
}
