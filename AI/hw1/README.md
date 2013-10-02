WGF2104: COMSW4701 (AI)
=======================
###Assignment 1: Pattern Matcher

---

**Environment:**

- GNU CLISP 2.49 (2010-07-07)
- running on MAC OS X 10.8.4 (12E55)

**Reasoning:**

This submission uses as a basis much of the Fall 2012 Assignment 1 specification offered at [https://www.cs.columbia.edu/~jvoris/AI/notes/assignment_1/assignment_1.htm](https://www.cs.columbia.edu/~jvoris/AI/notes/assignment_1/assignment_1.htm).
It retains much of the logic within the real-pattern-matcher (`rpm`) in the main conditional, which is abstracted by `match`, seen by the user (this handles the initial case with the empty association list).
A few checking methods are outsourced (`is-vbl`, `is-amp`, …) as well as the nested-list ampersand-clause recursion method.
I had some trouble implementing Kleene `*`, although it seemed to work in most cases; I believe this is possibly the result of a misused or misformatted `let`, and not the recursion logic. With more time, it should be immediately fixable.
All of the other functions are implemented robustly as specified in the assignment.

The only file to load for the assignment is pm.lisp.

