(defun direct_match(p d) ;p:pattern; d:data
	(cond
		((and (null (car p)) (null (car d))) t)
		((equal (car p)(car d)) (match (cdr p)(cdr d)))
		(T `NIL)
	)
)
 
 
 
(defun match (p d) 
	(rpm p d nil))


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


