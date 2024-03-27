# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
### Timetable plan component
In order to keep track of the timetable plan of the user, 3 classes are used mainly.
- `Course`: Represents a course taken or planned by the user
- `Timetable`: Stores the list of courses
- `PlanGetter`: Provides access to the timetable plan

Below is the class diagram to illustrate this:

![TimetablePlan_ClassDiagram](uml/TimetablePlan_ClassDiagram.png)

<br>

When `addCourse()` is called, it finds the suitable position to insert the newly added course according to the 
year and term. Then, the new course will be added into the list of courses.

Below is the sequence diagram to illustrate the implementation:

![AddCourse_SequenceDiagram](uml/AddCourse_SequenceDiagram.png)

<br>

When `removeCourse()` is called, it attempts to find the course with the given course code and removes the course 
from the list of courses if it exists.

Below is the sequence diagram to illustrate the implementation:

![RemoveCourse_SequenceDiagram](uml/RemoveCourse_SequenceDiagram.png)

<br>

There are 3 overloaded variations of the `getPlan()` method in the `PlanGetter` class.
The first is as shown in the class diagram above. 
The second takes in an additional integer `year`, while the third takes in two additional integers `year` and `term`.

Each of them loops through the timetable, getting the details of the courses in the course list.
The first variation outputs the details of all the courses in the plan, 
while the second and the third only pick out the specified year or term.

<br>

## Product scope
### Target user profile

Our target user profile is engineering students studying in NUS.

### Value proposition

The app aims to serve as a centralized platform for our target user to manage their past, current, and future courses 
based on their grades and interests. The app is able to keep a record of the user’s existing academic record and 
give the user relevant course information and future course recommendations.

## User Stories

|Version| As a ...                           | I want to ...                                                      | So that I can ...                                      |
|--------|------------------------------------|--------------------------------------------------------------------|--------------------------------------------------------|
|v1.0| new user                           | see usage instructions                                             | refer to them when I forget how to use the application |
|v1.0| new user                           | initialise my course plan based on my major's recommended schedule | populate the plan easily                               |
|v1.0| user                               | have my data auto-saved                                            | not worry about saving them                            |
|v1.0| user planning for future semesters | add new courses to the plan                                        | update the records                                     |
|v1.0| user planning for future semesters | remove courses from the plan                                       | change my mind                                         |
|v1.0| user planning for future semesters | view my course plan                                                | recall my plans                                        |
|v1.0| user recording past grades         | add my grades                                                      | keep track of my latest results                        |
|v1.0| user recording past grades         | modify my grades                                                   | keep track of my correct results                       |
|v1.0| user recording past grades         | check my grades along with the GPA calculated                      | see my results easily                                  |
|v2.0|                                    |                                                                    |                                                        |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
