#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

# reset saved file from previous run
if [ -e "save/tasks.txt" ]
then 
    rm save/tasks.txt
fi

cd ..
./gradlew clean shadowJar

cd text-ui-test

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL-UNIX.TXT

diff EXPECTED-UNIX.TXT ACTUAL-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi