MDS-handin-6
============

[__Formal Verification__](https://blog.itu.dk/SMDS-E2012/course-plan-and-curriculum/mandatory-assignment-6/)

##Progress

**Done:**
- Extend task manager xml.
- Create a Java client, 'VerificationClient.java',  for the VerificationService
- Create a Java client for the TaskmanagerHelperService
- Port and update relevant serialization classes.
- Fix live lock.
- Specify a custom property.

**TODO:**
- Finish README.md.

##Model Improvement
The given model has one single entry point, "order tests", since this task is the only one which does not have other tasks as its condition.
Since any other task has unfulfilled conditions, "order tests" is the only task that can be executed from the beginning.
This won't matter though, since after the execution, all other tasks still have unfulfilled conditions.
"sign" is one step closer to execution though, since "order tests" was a condition.
It still has "prescribe medicine" as a condition though.

After realizing that conditions are kind of transitive, it becomes clear that something is off.
"sign", "prescribe medicine", "examine results" and "perform tests" form a circle with their conditions.

Given that transitivity says that A -> B, B -> C => A -> C, and we have the following situation:
A -> B, B -> C, C -> D, D -> A; it becomes clear that any element, X, has the property X -> X, given their transitive properties.
In other words, the four tasks, "sign", "prescribe medicine", "examine results" and "perform tests", are in a situation where the task
cannot be executed before it has been executed.

In order to solve the problem, this circle needs to be broken.
Taking a closer look a the "sign" task, something seems off.
The "sign" task is used in two quite different contexts.
 1. When an order for some tests has been made, it can then be signed.
 2. When a prescription for some medicine has been made, it can then be signed.

Should a prescription be ready for a signature in order to sign an order for some tests? No!
Should a an order for some tests be ready for a signature in order to sign a prescription? No!
According to the graph, the tests should have been ordered, performed and examined, before prescribing the meds, yet, the conditions in "sign" states
that both the order and prescription should be signed at the same time.

How do we solve this then?
Well, it is now clear that "sign" is actually comprised of two tasks that clearly cannot be executed at the same time.
A solution would thus be to split "sign" up into two separate tasks, "sign order" and "sign prescription".
The full solution can be found in 'solution.xml'.
