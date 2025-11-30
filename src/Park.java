public class Park extends Building {
    public Park() {
        super("Park", GameConstants.PARK_BUILD_COST, GameConstants.PARK_MAINTENANCE);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(GameConstants.PARK_HAPPINESS_BOOST, 0, GameConstants.PARK_ENVIRONMENT_BOOST);
    }
}
