import java.util.Scanner;

public class ActionHandler {
    private City city;
    private UIDisplay display;
    private InputHandler inputHandler;
    private Scanner scanner;

    public ActionHandler(City city, UIDisplay display, InputHandler inputHandler, Scanner scanner) {
        this.city = city;
        this.display = display;
        this.inputHandler = inputHandler;
        this.scanner = scanner;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean buildInfrastructure() {
        display.displayBuildingMenu();

        Building building = inputHandler.getBuildingChoice();

        if (building == null) {
            System.out.println("❌ Cancelled or invalid choice.");
            display.waitForEnter(scanner);
            return false;
        }

        if (city.getBudget() >= building.getBuildCost()) {
            city.updateBudget(-building.getBuildCost());
            city.addBuilding(building);
            System.out.println("✓ " + building.getName() + " built successfully!");
            System.out.println("Budget remaining: $" + String.format("%.2f", city.getBudget()));
            display.waitForEnter(scanner);
            return true;
        } else {
            System.out.println("❌ Not enough budget! Need: $" + building.getBuildCost());
            display.waitForEnter(scanner);
            return false;
        }
    }

    public boolean repairBuildings() {
        display.displayRepairMenu(city);

        var damagedBuildings = city.getBuildings().stream()
                .filter(Building::isDamaged)
                .toList();

        if (damagedBuildings.isEmpty()) {
            display.waitForEnter(scanner);
            return false;
        }

        int choice = inputHandler.getRepairChoice(damagedBuildings.size() + 2);

        if (choice == damagedBuildings.size() + 2 || choice == -1) {
            System.out.println("❌ Cancelled.");
            display.waitForEnter(scanner);
            return false;
        }

        if (choice == damagedBuildings.size() + 1) {
            return repairAllBuildings(damagedBuildings);
        } else if (choice >= 1 && choice <= damagedBuildings.size()) {
            return repairSingleBuilding(damagedBuildings.get(choice - 1));
        } else {
            System.out.println("❌ Invalid choice!");
            display.waitForEnter(scanner);
            return false;
        }
    }

    private boolean repairAllBuildings(java.util.List<Building> damagedBuildings) {
        double totalCost = damagedBuildings.stream()
                .mapToDouble(Building::getRepairCost)
                .sum();

        if (city.getBudget() >= totalCost) {
            city.updateBudget(-totalCost);
            damagedBuildings.forEach(Building::repair);
            System.out.println("✓ All buildings repaired! Cost: $" + String.format("%.2f", totalCost));
            display.waitForEnter(scanner);
            return true;
        } else {
            System.out.println("❌ Not enough budget! Need: $" + String.format("%.2f", totalCost));
            display.waitForEnter(scanner);
            return false;
        }
    }

    private boolean repairSingleBuilding(Building building) {
        if (city.getBudget() >= building.getRepairCost()) {
            city.updateBudget(-building.getRepairCost());
            building.repair();
            System.out.println("✓ " + building.getName() + " repaired!");
            display.waitForEnter(scanner);
            return true;
        } else {
            System.out.println("❌ Not enough budget!");
            display.waitForEnter(scanner);
            return false;
        }
    }

    public boolean adjustTaxRate() {
        display.displayTaxRateMenu(city);

        int newRate = inputHandler.getTaxRateInput();

        if (newRate == -1) {
            System.out.println("❌ Invalid input!");
            display.waitForEnter(scanner);
            return false;
        }

        int oldRate = city.getTaxRate();
        city.setTaxRate(newRate);

        int happinessChange = (oldRate - newRate) * GameConstants.TAX_HAPPINESS_CHANGE_MULTIPLIER;
        city.updateStats(happinessChange, 0, 0);

        System.out.println("✓ Tax rate set to " + city.getTaxRate() + "%");
        if (happinessChange > 0) {
            System.out.println("Citizens are happy! Happiness +" + happinessChange);
        } else if (happinessChange < 0) {
            System.out.println("Citizens are upset! Happiness " + happinessChange);
        }
        display.waitForEnter(scanner);
        return true;
    }
}
