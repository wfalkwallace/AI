;p:pattern; d:data
(defun direct_match(p d)
	(cond
	 	;check if exhausted both lists, then true
		((and (null (car p)) (null (car d))) t)
		;if heads are equal, recurse on rest
		((equal (car p)(car d)) (direct_match (cdr p)(cdr d)))
		;if the lists aren't exhausted and the heads aren't equal 
		;(including if one is null) return no match
		(T NIL)
	)
)

;simple, user-faceing match
(defun match (p d) 
 	;calls real match with a new/empty assoc list
	(rpm p d nil)
)


(defun rpm (p d a)
	(Cond
		;we've successfully exhausted p and d, 
		;return the association list or t
		;(indicating non-associated match)
		( (and (null p) (null d)) 
			(cond 
				(a a)
				(T t)
			) 
		) 
		;we've only exhausted one; return failure
		( (or (null p) (null d)) NIL) 
		;check if next patter element is an
		;association variable 
		( (is-vbl (car p) ) 
			(cond
				;if it's a bound variable
				( (boundp (first p) a) 
				 	(cond
				 		;if its bound value is equal to the 
				 		;first data element, we return the 
				 		;real pattern match of the rest of the 
				 		;pattern with the rest of the data 
				 		;ie. if it's already been bound to
				 		;this element
						( (eql (first d) (assoc (first p) a)) (rpm (rest p)(rest d) a) ) 
						;here we know it's been bound
						;and the bound value isnt this one 
						;so we fail in the match (current branch)
						(T nil) 
					) 
				)
				;if its unbound, we bind it on the 
				;association list and similarly call
				;rpm recursively.
				( T (rpm (rest p)(rest d) (cons (list (first p) (first d)) a)) )
			) 
		)
		
		;for non-variable ?-matching
		( (eq `? (first p))
			;recurse on the rest of p and d
			(rpm (rest p)(rest d))
		)

		( (eq '* (first p))
			;recurse over both incr p and incr d
			;or is serial, so incr d first
			(Or (rpm (rest p) d a) (rpm p (rest d) a))
		)

		;Ok, now that that is done...
		;Now we check to see if p starts with 
		;a symbol that is not a variable, nor a 
		;a Kleene Star.
		( (atom (first p)) 
			;if so, check to see if it is equal to 
			;the first element of the data
			(cond
				( (eql (first p)(first d)) (rpm (rest p)(rest d) a) )
				(t nil)
			)
		) 
		;otherwise we failed
		;Now we know we have a list at the head of p
		;This is a little tricky so watch carefully:
		;I'm repairing a bug left behind in class.
		(t 
			(let (newa (rpm (first p)(first d) a) ) 
				;pattern match the sublists 
				(cond ( (null newa) nil) 
					;but it failed, so we fail
					;we succeeded, but newa could be "t" or
					;a binding list, so....
					( (listp newa) (rpm (rest p)(rest d) newa) ) 
					;Here newa is "t" so rpm with the association 
					;list of nil
					(t (rpm (rest p)(rest d) nil) )
				)
			)
		)
	)
)

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

(defun is-vbl (x) 
	(and (equal (elt x 0) #\?) (>= (length x) 2))
)

;(defun assoc ( x a-list ) ( ... ) )


