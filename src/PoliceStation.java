public class PoliceStation extends Building {
    public PoliceStation() {
        super("Police Station", GameConstants.POLICE_STATION_BUILD_COST, GameConstants.POLICE_STATION_MAINTENANCE);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(0, GameConstants.POLICE_STATION_SAFETY_BOOST, 0);
    }
}
