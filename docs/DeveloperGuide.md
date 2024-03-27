# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
 # testcase component
   # proposed implementation
     The proposed test mechanism is facilitated by ProblemGeneratorTest, CheckerTest. It check the correctness of the generated problemsets' types,
    number of questions and max digits, by comparing the generated output to the user input.

ProblemGeneratorTest#operatorTest() — Test if the input operator type is align with the generated one.
ProblemGeneratorTest#numberTest() — Test if the input number of questions is align with the generated number of questions.
ProblemGeneratorTest#digitTest() — Test if the input max digits is align with the generated one.
ProblemGeneratorTest#parseCommand() — parse the input command to fit in the program.
ProblemGeneratorTest#parseNumber() — parse out the operands from a given problem.

Given below is an example usage scenario and how the test behaves.

    Step 1. The user launch the ProblemGeneratorTest and run the operator testcase. The ProblemGeneratorTest#operatorTest() will loop through all the 
    commands in the data member "commands" and allocate each commands to its corresponding test case. During this process, a ProblemGenerator pb is 
    generated, and the problem sets it generates by calling ProblemGenerator#typeChoose will be store in variable #test#, then the problem set will be
    extracted using Test#getProblem(). After that, for every problem in the generated problem set, the assertTrue will check if the type of these problem 
    matches with the user input type. If all of them matches, it will successfully output the generated dataset, else, it will output the problem with 
    incorrect format.

    Step 2. The user launch the ProblemGeneratorTest and run the number testcase. The ProblemGeneratorTest#numberTest() will loop through all the commands
    in the data member #commands# and call ProblemGeneratorTest#parseCommand to parse the input command to get a hashmap with the input type, number and digits
    information. Then generate a new ProblemGenerator and use ProblemGenerator#typeChoose to collect the generated problem, then use assertEquals to compare 
    the user input number with the generated number of questions.
    
    Step 3. The user launch the ProblemGeneratorTest and run the digit testcase. The ProblemGeneratorTest#digitTest() will loop through all the commands
    in the data member #commands# and call ProblemGeneratorTest#parseCommand to parse the input command to get a hashmap with the input type, number and digits
    information. Then generate a new ProblemGenerator and use ProblemGenerator#typeChoose to collect the generated problem,then for every problem, call 
    ProblemGeneratorTest#parseNumbers to extract the digits in the problem, then use assertTrue to verify if the input max digit is greater or equal to the digits
    of every operands in the generated problems.
    


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

## Launch and Shutdown
   1. initial launch:
      1. Download the jar file and copy into an empty folder
      2. run the jar file use command '_java -jar tp.main.jar_'
   2. Saving window preferences

      1. Resize the window to an optimum size. Move the window to a different location. Close the window.

      2. Re-launch the app by double-clicking the jar file.
         Expected: The most recent window size and location is retained.
   3. Shutdown
      1. input command '_exit_' in the terminal after launch the program.
## Generate ProblemSet
   1. generate expected problem set by input its type('+ or - or * or /'), number of problems(integer) and
      the maximum digits.
      1. the example input: 'generate -t + -n 2 -d 3', which means generate a problem set, who has 2 problems,
         all of their operator is '+', and the maximum digit of their operands is 3 digits.
      2. input the answer: after all problems are generated, every problem will show on the terminal again,
         user can type his answer on the blank. Then the next problem will show as follows. After user type in
         all the answers, the accuracy of his performance and his speed will be shown as follows, e.g. 
        'Acc: 0.0
         Spend Time: 121s' .
   2. after shut down the program, user can view his performance report in a .txt file called 'recordList.txt' 
      with the complete date and accuracy inside.