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
java Entail backward kbs/0.txt Q >> log.txt
printf "====================\n" >> log.txt


printf "Query: DEVOUR \nKB: \n" >> log.txt
cat kbs/MM.txt >> log.txt
printf "\n\nForward Chaining:\n" >> log.txt
java Entail forward kbs/MM.txt DEVOUR >> log.txt
printf "\n\nBackward Chaining:\n" >> log.txt
java Entail backward kbs/MM.txt PIGN >> log.txt
printf "====================\n" >> log.txt


printf "Query: SUCCESS \nKB: \n" >> log.txt
cat kbs/GWH.txt >> log.txt
printf "\n\nForward Chaining:\n" >> log.txt
java Entail forward kbs/GWH.txt SUCCESS >> log.txt
printf "\n\nBackward Chaining:\n" >> log.txt
java Entail backward kbs/GWH.txt SUCCESS >> log.txt
printf "====================\n" >> log.txt


make clean