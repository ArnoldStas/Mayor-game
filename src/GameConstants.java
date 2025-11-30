public class GameConstants {

    // City Initial Values
    public static final int INITIAL_POPULATION = 1000;
    public static final double INITIAL_BUDGET = 50000;
    public static final int INITIAL_HAPPINESS = 50;
    public static final int INITIAL_SAFETY = 50;
    public static final int INITIAL_ENVIRONMENT = 50;
    public static final int INITIAL_TAX_RATE = 10;

    // Game Settings
    public static final int MAX_TURNS = 20;
    public static final int MIN_STAT_VALUE = 0;
    public static final int MAX_STAT_VALUE = 100;
    public static final int MIN_TAX_RATE = 0;
    public static final int MAX_TAX_RATE = 50;

    // Win/Lose Conditions
    public static final double MIN_BUDGET_TO_SURVIVE = 0;
    public static final int MIN_HAPPINESS_TO_SURVIVE = 20;
    public static final int MIN_POPULATION_TO_SURVIVE = 500;
    public static final int MIN_HAPPINESS_TO_WIN = 70;
    public static final double MIN_BUDGET_TO_WIN = 10000;

    // Tax & Revenue
    public static final double TAX_REVENUE_MULTIPLIER = 0.1;
    public static final int TAX_HAPPINESS_CHANGE_MULTIPLIER = 3;

    // Population Growth
    public static final int HAPPINESS_THRESHOLD_FOR_GROWTH = 70;
    public static final int HAPPINESS_THRESHOLD_FOR_DECLINE = 30;
    public static final double POPULATION_GROWTH_RATE = 0.02;
    public static final double POPULATION_DECLINE_RATE = 0.02;

    // Building Costs and Maintenance
    public static final double PARK_BUILD_COST = 5000;
    public static final double PARK_MAINTENANCE = 200;

    public static final double POLICE_STATION_BUILD_COST = 10000;
    public static final double POLICE_STATION_MAINTENANCE = 500;

    public static final double HOSPITAL_BUILD_COST = 15000;
    public static final double HOSPITAL_MAINTENANCE = 800;

    public static final double SCHOOL_BUILD_COST = 12000;
    public static final double SCHOOL_MAINTENANCE = 600;

    public static final double FACTORY_BUILD_COST = 20000;
    public static final double FACTORY_MAINTENANCE = 1000;

    public static final double REPAIR_COST_MULTIPLIER = 0.3;

    // Building Effects
    public static final int PARK_HAPPINESS_BOOST = 2;
    public static final int PARK_ENVIRONMENT_BOOST = 2;

    public static final int POLICE_STATION_SAFETY_BOOST = 3;

    public static final int HOSPITAL_HAPPINESS_BOOST = 2;
    public static final int HOSPITAL_SAFETY_BOOST = 1;

    public static final int SCHOOL_HAPPINESS_BOOST = 3;
    public static final int SCHOOL_POPULATION_BOOST = 5;

    public static final double FACTORY_REVENUE = 500;
    public static final int FACTORY_ENVIRONMENT_PENALTY = -2;

    // Random Events
    public static final double EVENT_PROBABILITY = 0.3;

    // Fire Event
    public static final int FIRE_HAPPINESS_PENALTY = -5;
    public static final int FIRE_SAFETY_PENALTY = -3;
    public static final int FIRE_ENVIRONMENT_PENALTY = -2;
    public static final double FIRE_BASE_COST = 1000;
    public static final double FIRE_REPAIR_COST_MULTIPLIER = 0.5;

    // Protest Event
    public static final int PROTEST_HAPPINESS_PENALTY = -10;
    public static final int PROTEST_SAFETY_PENALTY = -5;

    // Economic Boom Event
    public static final double ECONOMIC_BOOM_BASE_BONUS = 5000;
    public static final double ECONOMIC_BOOM_POPULATION_MULTIPLIER = 2;
    public static final int ECONOMIC_BOOM_HAPPINESS_BOOST = 5;

    // Natural Disaster Event
    public static final int NATURAL_DISASTER_MAX_DAMAGE_COUNT = 3;
    public static final double NATURAL_DISASTER_BASE_COST = 3000;
    public static final double NATURAL_DISASTER_POPULATION_MULTIPLIER = 1.5;
    public static final int NATURAL_DISASTER_HAPPINESS_PENALTY = -15;
    public static final int NATURAL_DISASTER_SAFETY_PENALTY = -10;
    public static final int NATURAL_DISASTER_ENVIRONMENT_PENALTY = -10;

    // Festival Request Event
    public static final double FESTIVAL_COST = 3000;
    public static final int FESTIVAL_HAPPINESS_BOOST = 15;
    public static final int FESTIVAL_DISAPPOINTMENT_PENALTY = -5;
}
