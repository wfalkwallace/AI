#!/bin/bash
# Date = Sun Oct 27 10:34:30 EST 2013
# Author = William Falk-Wallace
# Description = Script to run AI HW2 Tests

make


printf "====================\n" > log.txt


printf ">>Query: Q \n>>KB: \n" >> log.txt
cat kbs/0.txt >> log.txt
printf "\n\n>>Forward Chaining:\n" >> log.txt
java Entail forward kbs/0.txt Q >> log.txt
printf "\n\n>>Backward Chaining:\n" >> log.txt
java Entail backward kbs/0.txt Q >> log.txt
printf "====================\n" >> log.txt


printf ">>Query: DEVOUR \n>>KB: \n" >> log.txt
cat kbs/MM.txt >> log.txt
printf "\n\n>>Forward Chaining:\n" >> log.txt
java Entail forward kbs/MM.txt DEVOUR >> log.txt
printf "\n\n>>Backward Chaining:\n" >> log.txt
java Entail backward kbs/MM.txt DEVOUR >> log.txt
printf "====================\n" >> log.txt


printf ">>Query: SUCCESS \n>>KB: \n" >> log.txt
cat kbs/GWH.txt >> log.txt
printf "\n\n>>Forward Chaining:\n" >> log.txt
java Entail forward kbs/GWH.txt SUCCESS >> log.txt
printf "\n\n>>Backward Chaining:\n" >> log.txt
java Entail backward kbs/GWH.txt SUCCESS >> log.txt
printf "====================\n" >> log.txt


printf ">>Query: FUN \n>>KB: \n" >> log.txt
cat kbs/NEWPC.txt >> log.txt
printf "\n\n>>Forward Chaining:\n" >> log.txt
java Entail forward kbs/NEWPC.txt FUN >> log.txt
printf "\n\n>>Backward Chaining:\n" >> log.txt
java Entail backward kbs/NEWPC.txt FUN >> log.txt
printf "====================\n" >> log.txt


printf ">>Query: FAT \n>>KB: \n" >> log.txt
cat kbs/PIERNIKI.txt >> log.txt
printf "\n\n>>Forward Chaining:\n" >> log.txt
java Entail forward kbs/PIERNIKI.txt FAT >> log.txt
printf "\n\n>>Backward Chaining:\n" >> log.txt
java Entail backward kbs/PIERNIKI.txt FAT >> log.txt
printf "====================\n" >> log.txt


make clean