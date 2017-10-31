# BattleShips
Java-based Battleships project, written using BlueJ for Jakob Stein's end of year coursework for "Introduction to Programming" module at Durham University in 2013. 
BattleShips Strategy:
Grid of numbers= sum of possible ways a ship could fit there. 

1.place a given ship on the imaginary grid, then adding 1 to the cells the ship is on if it fits, iterate over all rows/columns : -vertical placement -horizontal placement
2. iterate for all ships (not yet sunk)
3. fire at square of highest magnitude


Int[][]
ArrayList: ships in play

takes input playing board

method:check which ships are in play- if any string of hits >1 have misses either side, count string length, compare to ships, remove ship with matching length from ships in play.
method:using a grid where every cell
