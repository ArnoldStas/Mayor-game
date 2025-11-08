public class Park extends Building {
    public Park() {
        super("Park", 5000, 200);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(2, 0, 2);
    }
}
