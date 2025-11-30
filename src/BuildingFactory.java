public class BuildingFactory {

    public enum BuildingType {
        PARK, POLICE_STATION, HOSPITAL, SCHOOL, FACTORY
    }

    public static Building createBuilding(BuildingType type) {
        if (type == null) {
            return null;
        }

        switch (type) {
            case PARK:
                return new Park();
            case POLICE_STATION:
                return new PoliceStation();
            case HOSPITAL:
                return new Hospital();
            case SCHOOL:
                return new School();
            case FACTORY:
                return new Factory();
            default:
                return null;
        }
    }

    public static Building createBuilding(int choice) {
        BuildingType type = getBuildingTypeFromChoice(choice);
        return createBuilding(type);
    }

    private static BuildingType getBuildingTypeFromChoice(int choice) {
        switch (choice) {
            case 1: return BuildingType.PARK;
            case 2: return BuildingType.POLICE_STATION;
            case 3: return BuildingType.HOSPITAL;
            case 4: return BuildingType.SCHOOL;
            case 5: return BuildingType.FACTORY;
            default: return null;
        }
    }
}
