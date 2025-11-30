import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selectCity() {
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
            return cityName;
        } catch (Exception e) {
            scanner.nextLine();
            return "New City";
        }
    }

    public int getMenuChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    public Building getBuildingChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) {
                return null;
            }

            return BuildingFactory.createBuilding(choice);
        } catch (Exception e) {
            scanner.nextLine();
            return null;
        }
    }

    public int getRepairChoice(int maxChoice) {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    public int getTaxRateInput() {
        try {
            int rate = scanner.nextInt();
            scanner.nextLine();
            return rate;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}
