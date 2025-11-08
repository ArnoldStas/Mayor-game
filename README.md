# City Mayor Game

A text-based city management simulation game where you play as a mayor managing a small city through strategic decision-making.

## Game Overview

Manage your city for 20 turns by:
- Building infrastructure (parks, police stations, hospitals, schools, factories)
- Balancing budget, happiness, safety, and environment
- Responding to random events
- Adjusting tax rates and managing expenses

## How to Build and Run

### Compile the game:
```bash
javac -d out src/*.java
```

### Run the game:
```bash
java -cp out Main
```

## Game Mechanics

### Starting Conditions
- **Population**: 1,000 citizens
- **Budget**: $50,000
- **Happiness**: 50/100
- **Safety**: 50/100
- **Environment**: 50/100
- **Tax Rate**: 10%

### Buildings

| Building | Cost | Maintenance | Effects |
|----------|------|-------------|---------|
| **Park** | $5,000 | $200/turn | +Happiness, +Environment |
| **Police Station** | $10,000 | $500/turn | +Safety |
| **Hospital** | $15,000 | $800/turn | +Happiness, +Safety |
| **School** | $12,000 | $600/turn | +Happiness, +Population growth |
| **Factory** | $20,000 | $1,000/turn | +Revenue, -Environment |

### Player Actions

Each turn you can:
1. **Build Infrastructure** - Construct new buildings
2. **Repair Buildings** - Fix damaged structures
3. **Adjust Tax Rate** - Change between 0-50% (affects revenue and happiness)
4. **View Building List** - See all constructed buildings
5. **View Detailed Report** - See comprehensive city statistics
6. **End Turn** - Pass to next turn

### Random Events (30% chance per turn)

- **🔥 Fire** - Damages buildings, costs money
- **📢 Protest** - Reduces happiness and safety
- **💰 Economic Boom** - Increases budget and happiness
- **⚠️ Natural Disaster** - Damages multiple buildings, high cost
- **🎉 Festival Request** - Spend money to boost happiness

### Win/Lose Conditions

**You LOSE if:**
- Budget reaches $0 or less
- Happiness drops below 20
- Population drops below 500

**You WIN if:**
- Successfully complete all 20 turns

## Game Features

### Core Mechanics
✅ Turn-based gameplay (20 turns total)
✅ Population tracking with growth/decline
✅ Budget management with tax revenue
✅ Multiple city statistics (Happiness, Safety, Environment)
✅ Building construction and maintenance
✅ Random events system
✅ Win/lose conditions

### Building System
✅ 5 different building types
✅ Buildings can be damaged by events
✅ Repair system for damaged buildings
✅ Ongoing maintenance costs
✅ Building effects on city stats

### Economic System
✅ Tax revenue based on population and tax rate
✅ Maintenance costs for all buildings
✅ Budget balance displayed each turn
✅ Tax rate adjustment affects happiness

### Population Dynamics
✅ Population grows when happiness is high (>70)
✅ Population declines when happiness is low (<30)
✅ Schools contribute to population growth

## Class Structure

```
Main.java                 - Entry point
GameController.java       - Game logic and user interface
City.java                 - City state management
Building.java             - Abstract building class
  ├─ Park.java
  ├─ PoliceStation.java
  ├─ Hospital.java
  ├─ School.java
  └─ Factory.java
RandomEvent.java          - Random event system
```

## Strategy Tips

1. **Balance Your Budget**: Don't build too many expensive buildings at once
2. **Maintain Happiness**: Keep citizens happy to avoid population loss
3. **Plan for Events**: Keep emergency funds for random disasters
4. **Tax Wisely**: Higher taxes = more money but unhappy citizens
5. **Repair Quickly**: Damaged buildings don't provide benefits
6. **Diversify**: Build a mix of buildings for balanced growth

## Technical Requirements

- Java 11 or higher
- Console/Terminal interface
- Single-player

## Example Gameplay

```
=== CITY STATUS ===
Turn: 1/20
Population: 1000
Budget: $50,000.00
Happiness: 50/100
Safety: 50/100
Environment: 50/100
Tax Rate: 10%
Buildings: 0

=== AVAILABLE ACTIONS ===
1. Build Infrastructure
2. Repair Buildings
3. Adjust Tax Rate (Current: 10%)
4. View Building List
5. View Detailed City Report
6. End Turn

Choose action (1-6):
```

## Development

This game demonstrates object-oriented programming principles:
- **Inheritance** - Building hierarchy with abstract base class
- **Encapsulation** - City state management with getters/setters
- **Abstraction** - Building abstract class with concrete implementations
- **Polymorphism** - Building effects applied through abstract method

## Future Enhancements

Potential additions:
- Save/Load game functionality
- Multiple cities management
- Seasonal effects
- Achievement system
- Building upgrade system
- More random events
- Difficulty levels

## License

Educational project for learning Java and game development.
