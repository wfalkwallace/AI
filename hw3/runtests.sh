#!/bin/bash
# Date = Sun Nov 21 10:44:30 EST 2013
# Author = William Falk-Wallace
# Description = Script to run AI HW2 Tests

echo "make"
make
echo ""
echo "Running GomoTest 5 3 10 2 2"
echo "GomoTest 5 3 10 2 2" > log.txt
echo "===================" > log.txt
java GomoTest 5 3 10 2 2 >> log.txt
echo "Completed GomoTest 5 3 10 2 2"
echo ""
echo "================================================" >> log.txt
echo "Running GomoTest 15 5 10 2 2"
echo "GomoTest 15 5 10 2 2" >> log.txt
echo "===================" > log.txt
java GomoTest 15 5 10 2 2 >> log.txt
echo "Completed GomoTest 15 5 10 2 2"
echo ""
echo "================================================" >> log.txt
echo "Running GomoTest 15 5 30 2 2"
echo "GomoTest 15 5 30 2 2" >> log.txt
echo "===================" > log.txt
java GomoTest 15 5 30 2 2 >> log.txt
echo "Completed GomoTest 15 5 30 2 2"
echo ""
echo "make clean"
make clean