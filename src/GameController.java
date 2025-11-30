import java.util.Scanner;

public class GameController {
    private City city;
    private Scanner scanner;
    private RandomEvent randomEvent;
    private boolean gameRunning;
    private UIDisplay display;
    private InputHandler inputHandler;
    private ActionHandler actionHandler;

    public GameController() {
        this.scanner = new Scanner(System.in);
        this.randomEvent = new RandomEvent();
        this.gameRunning = true;
        this.display = new UIDisplay();
        this.inputHandler = new InputHandler(scanner);
    }

    public void startGame() {
        display.displayWelcome();
        System.out.println("Press ENTER to start...");
        scanner.nextLine();

        String cityName = inputHandler.selectCity();
        this.city = new City(cityName);
        this.actionHandler = new ActionHandler(city, display, inputHandler, scanner);

        System.out.println("\nWelcome to " + cityName + ", Mayor!");
        System.out.println("Press ENTER to begin your administration...");
        scanner.nextLine();

        runGameLoop();

        scanner.close();
    }

    private void runGameLoop() {
        while (gameRunning && city.getCurrentTurn() <= GameConstants.MAX_TURNS) {
            playTurn();

            if (checkLoseConditions()) {
                display.displayGameOver(city);
                gameRunning = false;
                break;
            }
        }

        if (gameRunning && city.getCurrentTurn() > GameConstants.MAX_TURNS) {
            if (checkWinConditions()) {
                display.displayVictory(city);
            } else {
                display.displayDefeat(city);
            }
        }
    }

    private void playTurn() {
        display.clearScreen();

        System.out.println("=".repeat(60));
        city.displayStatus();
        System.out.println("=".repeat(60));

        randomEvent.generateEvent(city);
        if (randomEvent.hasEvent()) {
            randomEvent.displayEvent();
        }

        display.displayMenu(city);
        boolean actionPerformed = handlePlayerAction();

        if (actionPerformed) {
            city.nextTurn();
        }
    }

    private boolean handlePlayerAction() {
        int choice = inputHandler.getMenuChoice();

        switch (choice) {
            case 1:
                return actionHandler.buildInfrastructure();
            case 2:
                return actionHandler.repairBuildings();
            case 3:
                return actionHandler.adjustTaxRate();
            case 4:
                display.displayBuildingList(city);
                display.waitForEnter(scanner);
                return false;
            case 5:
                display.displayDetailedReport(city);
                display.waitForEnter(scanner);
                return false;
            case 6:
                System.out.println("\n⏭️ Ending turn...");
                return true;
            default:
                System.out.println("❌ Invalid choice! Turn skipped.");
                return false;
        }
    }

    private boolean checkLoseConditions() {
        if (city.getBudget() <= GameConstants.MIN_BUDGET_TO_SURVIVE) {
            System.out.println("\n💀 BANKRUPTCY! The city has run out of money!");
            return true;
        }

        if (city.getHappiness() < GameConstants.MIN_HAPPINESS_TO_SURVIVE) {
            System.out.println("\n💀 MASS EXODUS! Citizens are too unhappy and are leaving!");
            return true;
        }

        if (city.getPopulation() < GameConstants.MIN_POPULATION_TO_SURVIVE) {
            System.out.println("\n💀 GHOST TOWN! The population has dropped too low!");
            return true;
        }

        return false;
    }

    private boolean checkWinConditions() {
        if (city.getHappiness() < GameConstants.MIN_HAPPINESS_TO_WIN) {
            System.out.println("\n❌ FAIL! Citizens are not satisfied enough!");
            System.out.println("Happiness: " + city.getHappiness() + "/100 (Need at least " + GameConstants.MIN_HAPPINESS_TO_WIN + ")");
            return false;
        }

        if (city.getBudget() < GameConstants.MIN_BUDGET_TO_WIN) {
            System.out.println("\n❌ FAIL! City budget is too low!");
            System.out.println("Budget: $" + String.format("%.2f", city.getBudget()) + " (Need at least $" + String.format("%.0f", GameConstants.MIN_BUDGET_TO_WIN) + ")");
            return false;
        }

        return true;
    }
}
