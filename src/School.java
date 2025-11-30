public class School extends Building {
    public School() {
        super("School", GameConstants.SCHOOL_BUILD_COST, GameConstants.SCHOOL_MAINTENANCE);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(GameConstants.SCHOOL_HAPPINESS_BOOST, 0, 0);
        city.updatePopulation(GameConstants.SCHOOL_POPULATION_BOOST);
    }
}
