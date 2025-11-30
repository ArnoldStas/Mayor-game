import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City("Test City");
    }

    @Test
    public void testBudgetUpdate() {
        double initialBudget = city.getBudget();
        city.updateBudget(5000);
        assertEquals(initialBudget + 5000, city.getBudget(), 0.01, "Budget should increase by 5000");

        city.updateBudget(-3000);
        assertEquals(initialBudget + 2000, city.getBudget(), 0.01, "Budget should decrease by 3000");
    }

    @Test
    public void testStatsUpdateWithClamping() {
        city.updateStats(100, 100, 100);
        assertEquals(GameConstants.MAX_STAT_VALUE, city.getHappiness(), "Happiness should be clamped at max");
        assertEquals(GameConstants.MAX_STAT_VALUE, city.getSafety(), "Safety should be clamped at max");
        assertEquals(GameConstants.MAX_STAT_VALUE, city.getEnvironment(), "Environment should be clamped at max");

        city.updateStats(-200, -200, -200);
        assertEquals(GameConstants.MIN_STAT_VALUE, city.getHappiness(), "Happiness should be clamped at min");
        assertEquals(GameConstants.MIN_STAT_VALUE, city.getSafety(), "Safety should be clamped at min");
        assertEquals(GameConstants.MIN_STAT_VALUE, city.getEnvironment(), "Environment should be clamped at min");
    }

    @Test
    public void testBuildingEffectsOnCity() {
        int initialHappiness = city.getHappiness();
        int initialEnvironment = city.getEnvironment();

        Building park = new Park();
        city.addBuilding(park);
        park.applyEffects(city);

        assertEquals(initialHappiness + GameConstants.PARK_HAPPINESS_BOOST, city.getHappiness(),
                    "Happiness should increase by park boost");
        assertEquals(initialEnvironment + GameConstants.PARK_ENVIRONMENT_BOOST, city.getEnvironment(),
                    "Environment should increase by park boost");
    }

    @Test
    public void testBuildingDamageAndRepair() {
        Building hospital = new Hospital();
        assertFalse(hospital.isDamaged(), "New building should not be damaged");

        hospital.damage();
        assertTrue(hospital.isDamaged(), "Building should be damaged after damage() call");

        hospital.repair();
        assertFalse(hospital.isDamaged(), "Building should not be damaged after repair() call");
    }

    @Test
    public void testPopulationGrowthWhenHappinessHigh() {
        city.setHappiness(80);
        int initialPopulation = city.getPopulation();

        city.nextTurn();

        assertTrue(city.getPopulation() > initialPopulation,
                  "Population should grow when happiness is above " + GameConstants.HAPPINESS_THRESHOLD_FOR_GROWTH);
    }

    @Test
    public void testPopulationDeclineWhenHappinessLow() {
        city.setHappiness(20);
        int initialPopulation = city.getPopulation();

        city.nextTurn();

        assertTrue(city.getPopulation() < initialPopulation,
                  "Population should decline when happiness is below " + GameConstants.HAPPINESS_THRESHOLD_FOR_DECLINE);
    }

    @Test
    public void testDamagedBuildingsDoNotApplyEffects() {
        int initialHappiness = city.getHappiness();

        Building school = new School();
        school.damage();
        city.addBuilding(school);

        city.nextTurn();

        assertEquals(initialHappiness, city.getHappiness(),
                    "Damaged buildings should not apply effects during nextTurn()");
    }

    @Test
    public void testTaxRevenueCalculation() {
        double initialBudget = city.getBudget();
        int population = city.getPopulation();
        int taxRate = city.getTaxRate();

        city.nextTurn();

        double expectedRevenue = population * taxRate * GameConstants.TAX_REVENUE_MULTIPLIER;
        double actualBudgetIncrease = city.getBudget() - initialBudget;

        assertEquals(expectedRevenue, actualBudgetIncrease, 0.01,
                    "Tax revenue should be calculated correctly");
    }
}
