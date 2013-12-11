#!/bin/bash
# Date = Sun Oct 27 10:34:30 EST 2013
# Author = William Falk-Wallace
# Description = Script to run AI HW2 Tests

echo "Making executables:"
make
echo
echo $1 "chaining with query" $3 "on KB" $2":"
java Entail $1 $2 $3
echo
echo "Cleaning up:"
make clean