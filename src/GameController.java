import java.util.Scanner;

public class GameController {
    private City city;
    private Scanner scanner;
    private RandomEvent randomEvent;
    private boolean gameRunning;

    public GameController() {
        this.scanner = new Scanner(System.in);
        this.randomEvent = new RandomEvent();
        this.gameRunning = true;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void waitForEnter() {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    public void startGame() {
        displayWelcome();
        selectCity();

        while (gameRunning && city.getCurrentTurn() <= 20) {
            playTurn();

            if (checkLoseConditions()) {
                displayGameOver();
                gameRunning = false;
                break;
            }
        }

        if (gameRunning && city.getCurrentTurn() > 20) {
            if (checkWinConditions()) {
                displayVictory();
            } else {
                displayDefeat();
            }
        }

        scanner.close();
    }

    private void selectCity() {
        System.out.println("\n=== CHOOSE YOUR CITY ===");
        System.out.println("1. New York");
        System.out.println("2. Tokyo");
        System.out.println("3. Paris");
        System.out.println("4. London");
        System.out.println("5. Custom Name");
        System.out.print("\nSelect city (1-5): ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            String cityName;
            switch (choice) {
                case 1: cityName = "New York"; break;
                case 2: cityName = "Tokyo"; break;
                case 3: cityName = "Paris"; break;
                case 4: cityName = "London"; break;
                case 5:
                    System.out.print("Enter city name: ");
                    cityName = scanner.nextLine();
                    break;
                default:
                    cityName = "New City";
            }

            this.city = new City(cityName);
            System.out.println("\nWelcome to " + cityName + ", Mayor!");
            System.out.println("Press ENTER to begin your administration...");
            scanner.nextLine();
        } catch (Exception e) {
            this.city = new City("New City");
            scanner.nextLine();
        }
    }

    private void playTurn() {
        clearScreen();

        System.out.println("=".repeat(60));
        city.displayStatus();
        System.out.println("=".repeat(60));

        randomEvent.generateEvent(city);
        if (randomEvent.hasEvent()) {
            randomEvent.displayEvent();
        }

        displayMenu();
        boolean actionPerformed = handlePlayerAction();

        if (actionPerformed) {
            city.nextTurn();
        }
    }

    private void displayMenu() {
        System.out.println("\n=== AVAILABLE ACTIONS ===");
        System.out.println("1. Build Infrastructure");
        System.out.println("2. Repair Buildings");
        System.out.println("3. Adjust Tax Rate (Current: " + city.getTaxRate() + "%)");
        System.out.println("4. View Building List");
        System.out.println("5. View Detailed City Report");
        System.out.println("6. End Turn");
        System.out.print("\nChoose action (1-6): ");
    }

    private boolean handlePlayerAction() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    buildInfrastructure();
                    return true;
                case 2:
                    return repairBuildings();
                case 3:
                    adjustTaxRate();
                    return true;
                case 4:
                    viewBuildingList();
                    return false;
                case 5:
                    viewDetailedReport();
                    return false;
                case 6:
                    System.out.println("\n⏭️ Ending turn...");
                    return true;
                default:
                    System.out.println("❌ Invalid choice! Turn skipped.");
                    return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input! Turn skipped.");
            scanner.nextLine();
            return false;
        }
    }

    private void buildInfrastructure() {
        System.out.println("\n=== BUILD INFRASTRUCTURE ===");
        System.out.println("1. Park           - Cost: $5,000  | Maintenance: $200  | Effect: +Happiness +Environment");
        System.out.println("2. Police Station - Cost: $10,000 | Maintenance: $500  | Effect: +Safety");
        System.out.println("3. Hospital       - Cost: $15,000 | Maintenance: $800  | Effect: +Happiness +Safety");
        System.out.println("4. School         - Cost: $12,000 | Maintenance: $600  | Effect: +Happiness +Population");
        System.out.println("5. Factory        - Cost: $20,000 | Maintenance: $1,000| Effect: +Revenue -Environment");
        System.out.println("6. Cancel");
        System.out.print("\nChoose building (1-6): ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            Building building = null;

            switch (choice) {
                case 1: building = new Park(); break;
                case 2: building = new PoliceStation(); break;
                case 3: building = new Hospital(); break;
                case 4: building = new School(); break;
                case 5: building = new Factory(); break;
                case 6:
                    System.out.println("❌ Cancelled.");
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
                    return;
            }

            if (city.getBudget() >= building.getBuildCost()) {
                city.updateBudget(-building.getBuildCost());
                city.addBuilding(building);
                System.out.println("✓ " + building.getName() + " built successfully!");
                System.out.println("Budget remaining: $" + String.format("%.2f", city.getBudget()));
            } else {
                System.out.println("❌ Not enough budget! Need: $" + building.getBuildCost());
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
        waitForEnter();
    }

    private boolean repairBuildings() {
        System.out.println("\n=== REPAIR BUILDINGS ===");

        var damagedBuildings = city.getBuildings().stream()
                .filter(Building::isDamaged)
                .toList();

        if (damagedBuildings.isEmpty()) {
            System.out.println("✓ No damaged buildings!");
            waitForEnter();
            return false;
        }

        System.out.println("Damaged buildings:");
        for (int i = 0; i < damagedBuildings.size(); i++) {
            Building b = damagedBuildings.get(i);
            System.out.println((i + 1) + ". " + b.getName() + " - Repair cost: $" +
                    String.format("%.2f", b.getRepairCost()));
        }
        System.out.println((damagedBuildings.size() + 1) + ". Repair All");
        System.out.println((damagedBuildings.size() + 2) + ". Cancel");
        System.out.print("\nChoose option: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == damagedBuildings.size() + 2) {
                System.out.println("❌ Cancelled.");
                waitForEnter();
                return false;
            }

            if (choice == damagedBuildings.size() + 1) {

                double totalCost = damagedBuildings.stream()
                        .mapToDouble(Building::getRepairCost)
                        .sum();

                if (city.getBudget() >= totalCost) {
                    city.updateBudget(-totalCost);
                    damagedBuildings.forEach(Building::repair);
                    System.out.println("✓ All buildings repaired! Cost: $" + String.format("%.2f", totalCost));
                    waitForEnter();
                    return true;
                } else {
                    System.out.println("❌ Not enough budget! Need: $" + String.format("%.2f", totalCost));
                    waitForEnter();
                    return false;
                }
            } else if (choice >= 1 && choice <= damagedBuildings.size()) {
                Building building = damagedBuildings.get(choice - 1);
                if (city.getBudget() >= building.getRepairCost()) {
                    city.updateBudget(-building.getRepairCost());
                    building.repair();
                    System.out.println("✓ " + building.getName() + " repaired!");
                    waitForEnter();
                    return true;
                } else {
                    System.out.println("❌ Not enough budget!");
                    waitForEnter();
                    return false;
                }
            } else {
                System.out.println("❌ Invalid choice!");
                waitForEnter();
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
            waitForEnter();
            return false;
        }
    }

    private void adjustTaxRate() {
        System.out.println("\n=== ADJUST TAX RATE ===");
        System.out.println("Current tax rate: " + city.getTaxRate() + "%");
        System.out.println("Note: Higher taxes increase revenue but decrease happiness");
        System.out.println("      Lower taxes increase happiness but decrease revenue");
        System.out.print("\nEnter new tax rate (0-50): ");

        try {
            int newRate = scanner.nextInt();
            scanner.nextLine();

            int oldRate = city.getTaxRate();
            city.setTaxRate(newRate);

            int happinessChange = (oldRate - newRate) * 3;
            city.updateStats(happinessChange, 0, 0);

            System.out.println("✓ Tax rate set to " + city.getTaxRate() + "%");
            if (happinessChange > 0) {
                System.out.println("Citizens are happy! Happiness +" + happinessChange);
            } else if (happinessChange < 0) {
                System.out.println("Citizens are upset! Happiness " + happinessChange);
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input!");
            scanner.nextLine();
        }
        waitForEnter();
    }

    private void viewBuildingList() {
        System.out.println("\n=== BUILDING LIST ===");
        if (city.getBuildings().isEmpty()) {
            System.out.println("No buildings constructed yet.");
        } else {
            for (int i = 0; i < city.getBuildings().size(); i++) {
                Building b = city.getBuildings().get(i);
                System.out.println((i + 1) + ". " + b.toString() +
                        " | Maintenance: $" + b.getMaintenanceCost());
            }
        }
        System.out.println("\nTotal buildings: " + city.getBuildings().size());
        System.out.println("Total maintenance cost per turn: $" +
                city.getBuildings().stream().mapToDouble(Building::getMaintenanceCost).sum());
        waitForEnter();
    }

    private void viewDetailedReport() {
        System.out.println("\n=== DETAILED CITY REPORT ===");
        city.displayStatus();

        double taxRevenue = city.getPopulation() * city.getTaxRate() * 0.1;
        double maintenanceCost = city.getBuildings().stream()
                .mapToDouble(Building::getMaintenanceCost)
                .sum();

        System.out.println("\n--- Financial Breakdown (Next Turn Projection) ---");
        System.out.println("⚠️ NOTE: These are projections - no turn has been consumed yet!");
        System.out.println("Tax Revenue next turn: $" + String.format("%.2f", taxRevenue));
        System.out.println("Maintenance Cost next turn: $" + String.format("%.2f", maintenanceCost));
        System.out.println("Net Income next turn: $" + String.format("%.2f", (taxRevenue - maintenanceCost)));

        long damagedCount = city.getBuildings().stream().filter(Building::isDamaged).count();
        System.out.println("\n--- Building Status ---");
        System.out.println("Total Buildings: " + city.getBuildings().size());
        System.out.println("Damaged Buildings: " + damagedCount);
        System.out.println("Functional Buildings: " + (city.getBuildings().size() - damagedCount));
        waitForEnter();
    }

    private boolean checkLoseConditions() {
        if (city.getBudget() <= 0) {
            System.out.println("\n💀 BANKRUPTCY! The city has run out of money!");
            return true;
        }

        if (city.getHappiness() < 20) {
            System.out.println("\n💀 MASS EXODUS! Citizens are too unhappy and are leaving!");
            return true;
        }

        if (city.getPopulation() < 500) {
            System.out.println("\n💀 GHOST TOWN! The population has dropped too low!");
            return true;
        }

        return false;
    }

    private boolean checkWinConditions() {
        if (city.getHappiness() < 70) {
            System.out.println("\n❌ FAIL! Citizens are not satisfied enough!");
            System.out.println("Happiness: " + city.getHappiness() + "/100 (Need at least 70)");
            return false;
        }

        if (city.getBudget() < 10000) {
            System.out.println("\n❌ FAIL! City budget is too low!");
            System.out.println("Budget: $" + String.format("%.2f", city.getBudget()) + " (Need at least $10,000)");
            return false;
        }

        return true;
    }

    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       🏙️  WELCOME TO CITY MAYOR SIMULATION  🏙️");
        System.out.println("=".repeat(60));
        System.out.println("\nYou are the mayor of a small city!");
        System.out.println("Your goal: Successfully manage the city for 20 turns.");
        System.out.println("\n📋 Rules:");
        System.out.println("- Build infrastructure to improve city stats");
        System.out.println("- Balance budget, happiness, safety, and environment");
        System.out.println("- Random events will challenge your management skills");
        System.out.println("\n⚠️ Lose if:");
        System.out.println("- Budget reaches $0 or less");
        System.out.println("- Happiness drops below 20");
        System.out.println("- Population drops below 500");
        System.out.println("\n🏆 Win if:");
        System.out.println("- You survive all 20 turns AND");
        System.out.println("- Happiness is at least 70");
        System.out.println("- Budget is at least $10,000");
        System.out.println("\n⏰ Note: Viewing reports doesn't use turns!");
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Press ENTER to start...");
        scanner.nextLine();
    }

    private void displayGameOver() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ❌ GAME OVER ❌");
        System.out.println("=".repeat(60));
        System.out.println("\nYou were removed from office on Turn " + city.getCurrentTurn());
        System.out.println("\nFinal Statistics:");
        city.displayStatus();
        System.out.println("\n" + "=".repeat(60));
    }

    private void displayDefeat() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ❌ YOU DID NOT WIN ❌");
        System.out.println("=".repeat(60));
        System.out.println("\nYou survived 20 turns, but did not meet the win conditions!");
        System.out.println("\nFinal Statistics:");
        city.displayStatus();
        System.out.println("\n" + "=".repeat(60));
    }

    private void displayVictory() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          🎉 CONGRATULATIONS! 🎉");
        System.out.println("=".repeat(60));
        System.out.println("\nYou successfully managed the city for 20 turns!");
        System.out.println("\nFinal Statistics:");
        city.displayStatus();
        System.out.println("\n🏆 You are a legendary mayor!");
        System.out.println("=".repeat(60));
    }
}
