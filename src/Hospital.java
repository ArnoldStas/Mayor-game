public class Hospital extends Building {
    public Hospital() {
        super("Hospital", 15000, 800);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(2, 1, 0);
    }
}
