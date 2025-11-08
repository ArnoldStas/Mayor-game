public class School extends Building {
    public School() {
        super("School", 12000, 600);
    }

    @Override
    public void applyEffects(City city) {
        city.updateStats(3, 0, 0);
        city.updatePopulation(5);
    }
}
