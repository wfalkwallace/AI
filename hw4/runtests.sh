#!/bin/bash
# Date = Sun Oct 27 10:34:30 EST 2013
# Author = William Falk-Wallace
# Description = Script to run AI HW2 Tests

make


printf "====================\n" > log.txt


printf "Query: Q \nKB: \n" >> log.txt
cat kbs/0.txt >> log.txt
printf "\n\nForward Chaining:\n" >> log.txt
java Entail forward kbs/0.txt Q >> log.txt
printf "\n\nBackward Chaining:\n" >> log.txt
#java Entail "backward" "kbs/0.txt" "Q" >> log.txt
printf "====================\n" >> log.txt


printf "Query: PIGN \nKB: \n" >> log.txt
cat kbs/1.txt >> log.txt
printf "\n\nForward Chaining:\n" >> log.txt
java Entail forward kbs/1.txt PIGN >> log.txt
printf "\n\nBackward Chaining:\n" >> log.txt
#java Entail "backward" "kbs/1.txt" "PIGN" >> log.txt
printf "====================\n" >> log.txt


printf "Query: Q \nKB: \n" >> log.txt
cat kbs/2.txt >> log.txt
printf "\n\nForward Chaining:\n" >> log.txt
java Entail forward kbs/2.txt Q >> log.txt
printf "\n\nBackward Chaining:\n" >> log.txt
#java Entail backward kbs/2.txt Q >> log.txt
printf "====================\n" >> log.txt


make clean