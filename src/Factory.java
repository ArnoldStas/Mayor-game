public class Factory extends Building {
    public Factory() {
        super("Factory", 20000, 1000);
    }

    @Override
    public void applyEffects(City city) {
        city.updateBudget(500);
        city.updateStats(0, 0, -2);
    }
}
