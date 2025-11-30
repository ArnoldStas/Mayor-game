import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City("Test City");
    }

    @Test
    public void testWinConditionMet() {
        city.setHappiness(GameConstants.MIN_HAPPINESS_TO_WIN);
        city.setBudget(GameConstants.MIN_BUDGET_TO_WIN);

        assertTrue(city.getHappiness() >= GameConstants.MIN_HAPPINESS_TO_WIN,
                  "Win condition: happiness should be at least " + GameConstants.MIN_HAPPINESS_TO_WIN);
        assertTrue(city.getBudget() >= GameConstants.MIN_BUDGET_TO_WIN,
                  "Win condition: budget should be at least " + GameConstants.MIN_BUDGET_TO_WIN);
    }

    @Test
    public void testLoseConditionBankruptcy() {
        city.setBudget(0);

        assertTrue(city.getBudget() <= GameConstants.MIN_BUDGET_TO_SURVIVE,
                  "Lose condition: budget at or below " + GameConstants.MIN_BUDGET_TO_SURVIVE);
    }

    @Test
    public void testLoseConditionLowHappiness() {
        city.setHappiness(GameConstants.MIN_HAPPINESS_TO_SURVIVE - 1);

        assertTrue(city.getHappiness() < GameConstants.MIN_HAPPINESS_TO_SURVIVE,
                  "Lose condition: happiness below " + GameConstants.MIN_HAPPINESS_TO_SURVIVE);
    }

    @Test
    public void testLoseConditionLowPopulation() {
        city.updatePopulation(-(city.getPopulation() - GameConstants.MIN_POPULATION_TO_SURVIVE + 1));

        assertTrue(city.getPopulation() < GameConstants.MIN_POPULATION_TO_SURVIVE,
                  "Lose condition: population below " + GameConstants.MIN_POPULATION_TO_SURVIVE);
    }

    @Test
    public void testBuildingFactoryCreatesCorrectBuildings() {
        Building park = BuildingFactory.createBuilding(BuildingFactory.BuildingType.PARK);
        assertNotNull(park, "Factory should create a Park");
        assertTrue(park instanceof Park, "Created building should be instance of Park");

        Building hospital = BuildingFactory.createBuilding(BuildingFactory.BuildingType.HOSPITAL);
        assertNotNull(hospital, "Factory should create a Hospital");
        assertTrue(hospital instanceof Hospital, "Created building should be instance of Hospital");

        Building nullBuilding = BuildingFactory.createBuilding(null);
        assertNull(nullBuilding, "Factory should return null for null input");
    }
}
