#!/bin/bash
# Date = Sun Oct 27 10:34:30 EST 2013
# Author = William Falk-Wallace
# Description = Script to run AI HW2 Tests

make
java Entail "forward" "kbs/0.txt" "Q" > log.txt
java Entail "backward" "kbs/0.txt" "Q" >> log.txt
java Entail "forward" "kbs/1.txt" "PIGN" >> log.txt
java Entail "backward" "kbs/1.txt" "PIGN" >> log.txt
make clean