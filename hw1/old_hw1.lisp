(in-package "USER")

; Notes on Pattern Matching

; and Assignment #1

; 4701 - Artificial Intelligence

;Recall that we wrote and anlyzed the "equal" function in class. 
;A generalization of equality is computing the "similarity" of 
;two objects. This we call pattern matching.

;Here is the basic pattern matching function that "drives"

;the real pattern matcher (rpm). Note that it is called by

;our users without specifying a third argument. rpm, however, 
;requires an association list as its third argument. The

;association list encodes the "pattern variables and their bindings".

;Recall that our data can be ANY LISP s-expression including 
;atoms. Patterns may include pattern-variables (symbols that 
;have been defined by some means to be variables) and Kleene 
;star represented by the symbol "*".

; In a nutshell, rather than simply allowing 
;"?" pattern matching,

;we would also like to use variables to match data

;objects and be bound to them. These pattern variables are stored on an 
;"association list" we cart around thru the recursion. Read on.
(defun match (p d) (rpm p d nil))

;ok, now the real pattern matcher

;This function returns "nil" if it fails to match, "t" if it

;succeeds but finds NO V ARIABLES in the pattern, and "an association 
;list of variable bindings" in the case where it succeeds AND

;finds variables in the pattern.

;An association list is a list of pairs. Each pair is a list of

;two LISP values. The first LISP value is a symbol, the name of

;a pattern variable. The second LISP value is the data element 
;found to match the variable during the course of pattern matching.

;For example:

; (match '(a ? ?x b ) '(a 1 2 b)) matches and the returned value would 
;be ( (?x 2) ), i.e. it matched and the variable ?x was bound to 2.

; Note that a ? matches anything (a single unit (atom or list)) without 
; binding data to a variable.
(defun rpm (p d a)
(Cond
( (and (null p) (null d)) 
;we've exhausted both successfully (cond (a a)(t t))) 
;return association list or T
( (or (null p) (null d)) 
;we've exhausted one and not the other nil ) 
;return failure
( (is-vbl (first p)) 
;we've encountered a pattern variable (cond
((boundp (first p) a) 
;its a bound variable:
(cond

;if its bound value is equal to the 
;first data element, we return the 
;real pattern match of the rest of the 
;pattern with the rest of the data ((eql (first d) (assoc (first p) a)) (rpm (rest p)(rest d) a))

;here we know its bound value is unequal 
;so we fail in the match.
(t nil)))

;if its unbound, we bind it on the 
;association list and similarly call

;rpm recursively.
(t (rpm (rest p)(rest d) (cons (list (first p) (first d)) a)))))

;Ok, here is Kleene Star (for free!) You can 
;also include ? if you like preceeding this.
( (eq '* (first p))

;Kleene Star matches 0 or more elements, so...
(let ((newa (rpm (rest p) d a))) 
;See if we match 0 elements. 
;Note how this is accomplished. We advance

;p to the cdr of p in the recursion BUT DO

;NOT ADVANCE d.

;if so we return the new association list
(cond (newa newa)

;otherwise we try to match one data element
(t (rpm p (rest d) a)))))

;Note how this is accomplished. p is NOT

;ADV ANCED, but d is. In the recursive call

;p will still have kleene star as its head

;but d will be its cdr. Think about that! 
;******************NOTICE**************** 
;The entire "let" can be simply replaced!

;HOW? Well, here's how:

; (Or

; (rpm (rest p) d a)

; (rpm p (rest d) a) )

;Neat, huh?

;

;Ok, now that that is done...

;Now we check to see if p starts with 
;a symbol that is not a variable, nor a 
;a Kleene Star.
( (atom (first p)) 
;if so, check to see if it is equal to 
;the first element of the data
(cond ((eql (first p)(first d)) (rpm (rest p)(rest d) a)) (t nil))) 
;otherwise we failed

;Now we know we have a list at the head of p

;This is a little tricky so watch carefully:

;I'm repairing a bug left behind in class.
(t
(let ( newa (rpm (first p)(first d) a)) 
;pattern match the sublists (cond ( (null newa) nil) 
;but it failed, so we fail

;we succeeded, but newa could be "t" or

;a binding list, so....
( (listp newa) (rpm (rest p)(rest d) newa)) 
;Here newa is "t" so rpm with the association 
;list of nil
(t (rpm (rest p)(rest d) nil))))) ))

;and that's all folks!

; Lastly we have to define our variable definition functions, ie. a means

;of defining variables so our pattern matcher will know when its encountered 
;a pattern variable to distinguish it from an ordinary symbol. Well,

;that's left to you as an exercise. I'll write the defun's just to

;remind you that they have to be written. PLEASE NOTE that I used 
;"assoc" in rpm. Look it up. And here's a hint: pattern variables may

;have a new property associated with their symbol names, or may be collected 
;together in one master list of pattern variable symbols.....I guess that's

;more than a hint.

;(defun is-vbl (x) ( ... ) )

;(defun assoc ( x a-list ) ( ... ) )

; Well I'm feeling like I'm in a real good mood, so there aren't

;very many bugs left in this code. There may be one...check it out...

; There are very many more ways to write this function. It can be 
;written smaller, cleaner and faster. If you like puzzles, try working 
;out a better solution.

; By the way, did you note how to write comments in LISP code? 
;Any indeed, if you would like to run this code although its sitting 
;out on a file, you need to use the "load" function in lisp. "load" 
;does what it says: it loads a file of LISP definitions and expressions 
;and evaluates each one in turn. AFter loading a file, you can

;call any function that you have defined in that file. Try it and see 
;for yourself AFTER defining the is-vbl and assoc functions.

;Ok, now here is your First Assignment due in about two weeks.

;Extend the (possibly buggy) solution written above to include the use of 
;"predicates" in pattern matching. For example,

;(A $listp B) matches (A (b c) B), since the predicate #listp 
;is applied to the sublist (b c) and succeeds, but it fails to 
;match (A b B) since "b" is not a list.

;Define a collection of predicates you will provide in your pattern 
;matching language (for example, #listp, #numberp, #null) and implement 
;these predicates in your extended version of the pattern matcher.

;Next, define "predicate variables" as follows. Include in your

;pattern matcher the ability to test some condition using a previously 
;bound value of an existing variable. For example, the symbol < X 
;means that this symbol when encountered in a pattern will successfully 
;match a data element ONLY IF the value of the data element is less than 
;the value already bound to the variable X.

;So,(A ?xb(!<?x))matches(A 2b1),butdoesnotmatch(A 2b 34).

;(Why? Because initially the ?x matched and was bound to the number 2. 
;When the ( ! < ?x) was encountered, the datum item 34 is NOT less than 2.)

;define predicate variables with a convention as such:

; ( ! predicate-name variable-name)

;The "shreak" symbol (or some other of your choice) represents that

;this pattern object contains a predicate to apply followed by the name

;of a previously bound variable. If the variable name is missing, then

;only a predicate name is supplied. (Y ou can tell the difference by 
;counting the number of symbols following the exclamation point (shreak)).

;Define a collection of predicates that you will use in your predicate 
;variables and implement them accordingly. You don't need to do too

;many. But you should do a sufficient number to ensure your implementation 
;works.

;One very important predicate that you MUST include is the predicate

;#SIMILAR. The "similar" predicate decides whether two LISP SYMBOLS

;are deemed similar! For example, the symbol RED might be defined

;as similar to the symbol MAROON and when both are encountered the

;pattern matcher will accept them. This is the ONLY thinking part

;of the assignment: HOW DO YOU REPRESENT SIMILAR SYMBOLS....hmmmmmmmmm?

;So, for example, given the following, the pattern matcher should say 
;yeah...they match:

;

;pattern: ( ?object (Color (! #Similar RED )) (Taste Horrible) (Size ?) ) 
;

;data: ( football (Color BROWN) (Taste Horrible) (Size football-size)) 
;

;where previously we defined: (SIMILAR 'RED 'BROWN)

;Have fun.

;HERE IS A TEST SUITE YOU OUGHT TO INPUT INTO YOUR FINISHED PROJECT:
;For brevity, the output of the commands (function evaluations) is indicated after the sign "=>". 
;The output of the function similar depends on the implementation. 
;I implemented the similarity with an association list, but it could be done differently.
(match '() '()) =>T
(match '(ai) '(ai)) =>T
(match '(ai cs) '(ai cs)) =>T
(match '(cs ai) '(ai cs)) =>NIL
(match '(1 2 3 0) '(1 2 3 4 0)) => NIL
(match '(? mudd) '(seely mudd)) =>T
(match '(?first ?middle mudd) '(seely w mudd)) => ((?MIDDLE W) (?FIRST SEELY)) (match '(? ?x ? ?y ?) '(Bill Gates Is A Good Man)) => NIL
(match '(School Of Engineering and Applied Science) '(School Of Engineering)) => NIL
(match '(* School Of Engineering and Applied Science) '(The Fu Foundation School Of Engineering and Applied Science)) => T
(match '(The * School Of Engineering and Applied Science) '(The Fu Foundation School Of Engineering and Applied Science)) => T
(match '(The * School Of Engineering and Applied Science) '(The School Of Engineering and Applied Science)) => T
(match '(* 3 ?x 4 *) '(3 5 4)) => T
USER(13): (match '(we ?er $numberp ?er $listp e t) '(we gh 7 gh (5 6 7) e t)) => ((?ER GH)) USER(14): (match '(df $null 67 $null t y) '(df () 67 nil t y)) => T
(match '($listp) '((2))) => T
(match '(?y e (! < 23) c 4) '(98 e 6 c 4))
((?Y 98))
(match '(?x 4 (! < 20) 4 ?y $numberp) '(10 4 3 4 5 256)) => ((?Y 5) (?X 10)) (match '(?z $numberp (! > ?z) *) '(13 1024 7)) => NIL
(match '(er (! numberp) c 4) '(er 6 c 4)) => T
(similar 'orange 'mango) => ((ORANGE . MANGO))
(similar 'mango 'strawberry) => ((MANGO . STRAWBERRY) (ORANGE . MANGO))
(similar 'lewinsky 'jones) => ((LEWINSKY . JONES) (MANGO . STRAWBERRY) (ORANGE . MANGO))
(match '(?president (! $similar jones)) '(clinton lewinsky)) => ((?president clinton))
(match '((! $similar orange)) '(strawberry)) => NIL
(match '((! $similar orange)) '(mango)) => T
(match '( ?x (1 2) ?y (4 5)) '(c (1 2) d (4 5))) => ((?Y D) (?X C)) (match '(?y ?z (c v)) '(8 gh (c v) )) => ((?Z GH) (?Y 8))
(match '(((get) me) out) '(get (me (out)))) => NIL