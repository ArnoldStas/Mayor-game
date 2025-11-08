public class PoliceStation extends Building {
    public PoliceStation() {
        super("Police Station", 10000, 500);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(0, 3, 0);
    }
}
