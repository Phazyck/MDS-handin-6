#[Mandatory Assignment 6: Formal Verification](https://blog.itu.dk/SMDS-E2012/course-plan-and-curriculum/mandatory-assignment-6/)

##Introduction

__Everything you need to know:__

__*Adopted Design:*__ This project has a rather simple design.
As usual, we have packages for serialization/deserialization to/from XML.
Next, we have generated clients for the supplied web services.
To take care of reading/writing files, we have made a FileOrganizer.
All of these modules are then used by the TaskManagerVerifier, which can be utilized through the ConsoleVerifier UI.

__*External dependencies:*__ This project has no external dependencies. All imported classes/packages are in the standard JDK.

__*Screen dumps:*__ In the files folder, all input and output from different runs is grouped in a very straightforward folder structure.


##Package Overview

__Brief descriptions of all the packages in the project:__ 

 * __clients:__ Contains clients to the supplied web services.
 * __util:__ Contains a single utility class, the FileOrganizer.
 * __verification:__ Contains the main classes for performing model verification.
 * __xml:__ Contains the core classes necessary for serialization/deserialization to/from XML.
 * __xml.verification:__ Contains Java class representations of various XML elements appearing in the verification results.

##The 'files' folder

__The place where all the good stuff gathers:__ 

 * __/example:__ Contains the example task manager from the notes, and related result output.
 * __/extended:__ Contains the extended task manager from the assignment, and related result output.
 * __/improved:__ Contains the improved task manager, (based on the extended one), and related result output.
 * __/properties:__ Contains the properties to verify on a given task manager, and related result output.

##Execution

__In order to execute and test this project, you should do the following:__

 - Run "ConsoleVerification.java".
 - Follow the instructions provided in the console.

__*Post-execution:*__ Depending on which task manager file and property have been used, the corresponding folder, (see 'files' section), will not have several additional files containing the different results.


##The Improved Model

__The thought-process behind the improved model:__

prescribe-medicine-extended.xml has one major flaw, it does not fulfill the 'liveness' property.
We have come to the following conclusion as to why that is:
The given model has one single entry point, "order tests", since this task is the only one which does not have other tasks as its condition.
Even after executing this task, all other tasks are still unable to execute, since they all still have unfulfilled conditions.
When looking at the model, it becomes clear that "sign" is the knot that ties it all together.

In order to execute "sign", "prescribe medicine" must be executed.
In order to execute "prescribe medicine", "examine results" must be executed.
It seems that conditions are transitive in some sense.
Having this in mind, the cycle of conditions reveal something fishy; "sign" ends up being a condition to "sign".

But wait a minute, how can that be?! Let's go through the intended flow of the model:
 1. A doctor orders some tests. 
 2. A doctor signs the order.
 3. A nurse performs some ordered tests.
 4. A doctor examines some test results.
 5. A doctor prescribes some medicine. 
 6. A doctor signs a prescription.
    - (A nurse doesn't trust the prescription.)
    - (A doctor signs another prescription.)
 7. A nurse gives a patient some medicine.

The "sign" task appears at two scenarios, (2) and (6).
These two scenarios aren't exactly the same though, in (2), an order for some tests is signed, while in (6), a prescription for some meds is signed.

It seems obvious that "sign" should be divided into two tasks "sign order" and "sign prescription".
In our "improved" model, this is exactly what we've done.
