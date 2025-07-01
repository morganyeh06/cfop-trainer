## CFOP Trainer
A Java application for learning and practicing Rubik's Cube OLL and PLL algorithms.

### Built With
- Java
- Apache Ant

## The CFOP Method for Solving Rubik's Cubes
The CFOP method is the most common speedsolving method for the 3x3 Rubik's Cube, consisting of 4 steps: Cross, F2L, OLL, and PLL. The final steps are the most algorithm-intensive, with 78 algorithms needed to solve every last layer case. 

### OLL
OLL (short for Orientation of the Last Layer) is the penultimate step in the CFOP method which involves orienting the last layer pieces. There are 57 algorithms in total, but a variation known as 2-Look OLL exists which splits it up into two steps. 2-Look OLL first uses one algorithm to orient the edges, then another to orient the corners, which significantly decreases the number of algorithms that are needed.

### PLL
PLL (short for Permutation of the Last Layer) is the final step in the CFOP method which involves solving the remaining last layer pieces. There are 21 algorithms in total, but a variation known as 2-Look PLL exists which splits it up into two steps. 2-Look PLL first uses one algorithm to solve the corners, then another to solve the edges, which significantly decreases the number of algorithms that are needed.

## Usage
### Learning Algorithms
1. Select _Algorithms_ in the main menu
2. Choose one of the following alg sets to view:
   - OLL
   - 2-Look OLL
   - PLL
   - 2-Look PLL
3. Select or deselect the checkboxes to indicate whether the algorithms have been learned

### Training Algorithms
1. Select _Alg Trainers_ in the main menu
2. Choose one of the following alg trainers:
   - OLL Trainer
   - PLL Trainer
3. Select the cases you would like to practice
4. Click _Train_ to practice algs randomly, or _Recap_ to view each case once
5. Follow the scramble given at the top of the screen, use the spacebar to start/stop the timer
