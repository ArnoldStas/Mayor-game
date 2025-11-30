public class Hospital extends Building {
    public Hospital() {
        super("Hospital", GameConstants.HOSPITAL_BUILD_COST, GameConstants.HOSPITAL_MAINTENANCE);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(GameConstants.HOSPITAL_HAPPINESS_BOOST, GameConstants.HOSPITAL_SAFETY_BOOST, 0);
    }
}
