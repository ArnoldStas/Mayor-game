public class Factory extends Building {
    public Factory() {
        super("Factory", GameConstants.FACTORY_BUILD_COST, GameConstants.FACTORY_MAINTENANCE);
    }

    @Override
    public void applyEffects(City city) {
        city.updateBudget(GameConstants.FACTORY_REVENUE);
        city.updateStats(0, 0, GameConstants.FACTORY_ENVIRONMENT_PENALTY);
    }
}
