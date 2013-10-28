#!/bin/bash
# Date = Sun Oct 27 10:34:30 EST 2013
# Author = William Falk-Wallace
# Description = Script to run AI HW2 Tests

make
java SokoTest "levels/0.txt" 1 y > log.txt
java SokoTest "levels/1.txt" 1 y >> log.txt
java SokoTest "levels/3.txt" 1 y >> log.txt
java SokoTest "levels/0.txt" 2 y >> log.txt
java SokoTest "levels/1.txt" 2 y >> log.txt
java SokoTest "levels/3.txt" 2 y >> log.txt
java SokoTest "levels/0.txt" 3 y >> log.txt
java SokoTest "levels/1.txt" 3 y >> log.txt
java SokoTest "levels/3.txt" 3 y >> log.txt
java SokoTest "levels/0.txt" 4 y >> log.txt
java SokoTest "levels/1.txt" 4 y >> log.txt
java SokoTest "levels/3.txt" 4 y >> log.txt
java SokoTest "levels/0.txt" 5 y >> log.txt
java SokoTest "levels/1.txt" 5 y >> log.txt
java SokoTest "levels/3.txt" 5 y >> log.txt
java SokoTest "levels/0.txt" 6 y >> log.txt
java SokoTest "levels/1.txt" 6 y >> log.txt
java SokoTest "levels/3.txt" 6 y >> log.txt
java SokoTest "levels/0.txt" 7 y >> log.txt
java SokoTest "levels/1.txt" 7 y >> log.txt
java SokoTest "levels/3.txt" 7 y >> log.txt
make clean