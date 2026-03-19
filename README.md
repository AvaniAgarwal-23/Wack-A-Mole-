# Wack-A-Mole-
Java OOP. Three packages: pokemon (types &amp; abstract class), trainer (Trainer logic), oak (battle engine &amp; menu). Supports showdowns, win/loss tracking, and type matchups (Water>Fire>Grass>Water, Electric>Water). Run: java oak.OakJournal

## How It Works
### Battle System
When a showdown is run between two trainers, each of their 6 slots is matched up one-by-one. The winner of each round is determined by **type matchups**, falling back to **HP comparison** if no type advantage applies.

**Type Matchup Rules:**
| Attacker | Defender | Winner |
|----------|----------|--------|
| Water    | Fire     | Water  |
| Fire     | Grass    | Fire   |
| Grass    | Water    | Grass  |
| Electric | Water    | Electric |
| (tie)    | —        | Higher HP wins |

The trainer who wins more rounds wins the overall battle. A win gives +1 badge; a loss removes 1 badge and increments loss count.
---
## Getting Started

### Prerequisites
- Java JDK 8 or higher
- VS Code with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

### Compiling
From the `GradedLab1/` root directory:
```bash
javac pokemon/*.java trainer/*.java oak/*.java
```

### Running
```bash
java oak.OakJournal
```
---
## Interactive Menu
Once launched, a default showdown between trainers **Android** and **Apple** runs automatically. Then the following menu appears:

```
---- Oak Journal Menu -------
| 1. Add Trainer              |
| 2. Add Pokemon to Trainer   |
| 3. Run Showdown             |
| 4. Exit                     |
 ---------------------------
```
- **Option 1** – Add a new trainer by name
- **Option 2** – Add a Pokémon to a trainer's slot (choose type, slot, name, HP, damage)
- **Option 3** – Run a showdown between any two registered trainers
- **Option 4** – Exit the program
---
## OOP Concepts Used
- **Abstract classes** – `Pokemon` is abstract; `getType()` must be overridden
- **Inheritance** – Type classes extend `Pokemon`; specific Pokémon extend type classes
- **Encapsulation** – Private fields with getters/setters throughout
- **Packages** – Code is organized into `pokemon`, `trainer`, and `oak` packages
