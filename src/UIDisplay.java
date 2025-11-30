import java.util.Scanner;

public class UIDisplay {

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void waitForEnter(Scanner scanner) {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    public void displayWelcome() {
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
    }

    public void displayMenu(City city) {
        System.out.println("\n=== AVAILABLE ACTIONS ===");
        System.out.println("1. Build Infrastructure");
        System.out.println("2. Repair Buildings");
        System.out.println("3. Adjust Tax Rate (Current: " + city.getTaxRate() + "%)");
        System.out.println("4. View Building List");
        System.out.println("5. View Detailed City Report");
        System.out.println("6. End Turn");
        System.out.print("\nChoose action (1-6): ");
    }

    public void displayBuildingList(City city) {
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
    }

    public void displayDetailedReport(City city) {
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
    }

    public void displayGameOver(City city) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ❌ GAME OVER ❌");
        System.out.println("=".repeat(60));
        System.out.println("\nYou were removed from office on Turn " + city.getCurrentTurn());
        System.out.println("\nFinal Statistics:");
        city.displayStatus();
        System.out.println("\n" + "=".repeat(60));
    }

    public void displayDefeat(City city) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ❌ YOU DID NOT WIN ❌");
        System.out.println("=".repeat(60));
        System.out.println("\nYou survived 20 turns, but did not meet the win conditions!");
        System.out.println("\nFinal Statistics:");
        city.displayStatus();
        System.out.println("\n" + "=".repeat(60));
    }

    public void displayVictory(City city) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          🎉 CONGRATULATIONS! 🎉");
        System.out.println("=".repeat(60));
        System.out.println("\nYou successfully managed the city for 20 turns!");
        System.out.println("\nFinal Statistics:");
        city.displayStatus();
        System.out.println("\n🏆 You are a legendary mayor!");
        System.out.println("=".repeat(60));
    }

    public void displayBuildingMenu() {
        System.out.println("\n=== BUILD INFRASTRUCTURE ===");
        System.out.println("1. Park           - Cost: $5,000  | Maintenance: $200  | Effect: +Happiness +Environment");
        System.out.println("2. Police Station - Cost: $10,000 | Maintenance: $500  | Effect: +Safety");
        System.out.println("3. Hospital       - Cost: $15,000 | Maintenance: $800  | Effect: +Happiness +Safety");
        System.out.println("4. School         - Cost: $12,000 | Maintenance: $600  | Effect: +Happiness +Population");
        System.out.println("5. Factory        - Cost: $20,000 | Maintenance: $1,000| Effect: +Revenue -Environment");
        System.out.println("6. Cancel");
        System.out.print("\nChoose building (1-6): ");
    }

    public void displayRepairMenu(City city) {
        System.out.println("\n=== REPAIR BUILDINGS ===");

        var damagedBuildings = city.getBuildings().stream()
                .filter(Building::isDamaged)
                .toList();

        if (damagedBuildings.isEmpty()) {
            System.out.println("✓ No damaged buildings!");
            return;
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
    }

    public void displayTaxRateMenu(City city) {
        System.out.println("\n=== ADJUST TAX RATE ===");
        System.out.println("Current tax rate: " + city.getTaxRate() + "%");
        System.out.println("Note: Higher taxes increase revenue but decrease happiness");
        System.out.println("      Lower taxes increase happiness but decrease revenue");
        System.out.print("\nEnter new tax rate (0-50): ");
    }
}
