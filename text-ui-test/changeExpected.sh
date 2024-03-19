#!/usr/bin/env bash

# reset saved file from previous run
if [ -e "save/tasks.txt" ]
then 
    rm save/tasks.txt
fi

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./EXPECTED-UNIX.TXT" ]
then 
    rm EXPECTED-UNIX.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp . -d ../bin $(find ../src/main/java -name "*.java" -print)
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the EXPECTED-UNIX.TXT
if java -classpath ../bin seedu/duke/Main < input.txt > EXPECTED-UNIX.TXT; then
    echo "EXPECTED-UNIX.TXT changed according to the output from commands in input.txt!"
else
    echo "An error occurred"
    exit 1
fi