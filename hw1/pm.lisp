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

;simple, user-facing match
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
		;we've only exhausted one; fail
		( (or (null p) (null d)) NIL) 
		
		;?X
		;check if next patter element is an
		;association variable 
		( (is-vbl (car p) ) 
		 	; (format t "(is-vbl(~S) -> TRUE)~%" (car p))
			(cond
				;if it's a bound variable
				( (bound (first p) a) 
					; (format t "(~S IS bound)~%" (car p))
				 	(cond
				 		;if its bound value is equal to the 
				 		;first data element, we return the 
				 		;real pattern match of the rest of the 
				 		;pattern with the rest of the data 
				 		;ie. if it's already been bound to
				 		;this element
						( (eql (first d) (first (rest (assoc (first p) a)))) (rpm (rest p) (rest d) a) ) 
						; (t (format t "(~S -> ~S NOT ~S)~%" (car p) (cdr (assoc (first p) a)) (list (first d)) ))
						;here we know it's been bound
						;and the bound value isnt this one 
						;so we fail in the match (current branch)
						(T NIL) 
					) 
				)
				;if its unbound, we bind it on the 
				;association list and similarly call
				;rpm recursively.
				(T (rpm (rest p) (rest d) (cons (list (first p) (first d)) a)))
			) 
		)
		
		;NOT
		( (is-not (car p) ) 
		 	; (format t "(is-not(~A): ~A -> ~A)~%" (car d) (concatenate 'string "?" (string (elt (symbol-name (first p)) 1))) (first (rest (assoc (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a))) )
			(cond
				;if it's a bound variable
				( (bound (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a) 
					; (format t "(~S IS bound)~%" (car p))
				 	(cond
				 		;if its bound value is equal to the 
				 		;first data element, we return the 
				 		;real pattern match of the rest of the 
				 		;pattern with the rest of the data 
				 		;ie. if it's already been bound to
				 		;this element
						( (not (eql (first d) (first (rest (assoc (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a))))) (rpm (rest p) (rest d) a) ) 
						; (t (format t "(~S -> ~S NOT ~S)~%" (car p) (cdr (assoc (first p) a)) (list (first d)) ))
						;here we know it's been bound
						;and the bound value isnt this one 
						;so we fail in the match (current branch)
						(T NIL) 
					) 
				)
				;if its unbound, we bind it on the 
				;association list and similarly call
				;rpm recursively.
				(T (rpm (rest p) (rest d) a))
			) 
		)

		;<
		( (is-lt (car p) ) 
		 	; (format t "(is-not(~A): ~A -> ~A)~%" (car d) (concatenate 'string "?" (string (elt (symbol-name (first p)) 1))) (first (rest (assoc (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a))) )
			(cond
				;if it's a bound variable
				( (bound (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a) 
					; (format t "(~S IS bound)~%" (car p))
				 	(cond
				 		;if its bound value is equal to the 
				 		;first data element, we return the 
				 		;real pattern match of the rest of the 
				 		;pattern with the rest of the data 
				 		;ie. if it's already been bound to
				 		;this element
						( (< (first d) (first (rest (assoc (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a)))) (rpm (rest p) (rest d) a) ) 
						; (t (format t "(~S -> ~S NOT ~S)~%" (car p) (cdr (assoc (first p) a)) (list (first d)) ))
						;here we know it's been bound
						;and the bound value isnt this one 
						;so we fail in the match (current branch)
						(T NIL) 
					) 
				)
				;if its unbound, we bind it on the 
				;association list and similarly call
				;rpm recursively.
				(T (rpm (rest p) (rest d) a))
			) 
		)

		;>
		( (is-gt (car p) ) 
		 	; (format t "(is-not(~A): ~A -> ~A)~%" (car d) (concatenate 'string "?" (string (elt (symbol-name (first p)) 1))) (first (rest (assoc (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a))) )
			(cond
				;if it's a bound variable
				( (bound (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a) 
					; (format t "(~S IS bound)~%" (car p))
				 	(cond
				 		;if its bound value is equal to the 
				 		;first data element, we return the 
				 		;real pattern match of the rest of the 
				 		;pattern with the rest of the data 
				 		;ie. if it's already been bound to
				 		;this element
						( (> (first d) (first (rest (assoc (intern (concatenate 'string "?" (string (elt (symbol-name (first p)) 1)))) a)))) (rpm (rest p) (rest d) a) ) 
						; (t (format t "(~S -> ~S NOT ~S)~%" (car p) (cdr (assoc (first p) a)) (list (first d)) ))
						;here we know it's been bound
						;and the bound value isnt this one 
						;so we fail in the match (current branch)
						(T NIL) 
					) 
				)
				;if its unbound, we bind it on the 
				;association list and similarly call
				;rpm recursively.
				(T (rpm (rest p) (rest d) a))
			) 
		)
		
		;for non-variable ?-matching
		( (eq '? (first p))
			;recurse on the rest of p and d
			(rpm (rest p) (rest d) a)
		)

		( (eq '* (first p))
			;recurse over both incr p and incr d
			;or is serial, so incr d first
			(Or 
				(rpm (rest p) d a) 
				(rpm p (rest d) a)
			)
			
			; ;Kleene Star matches 0 or more elements, so...
			; (let 
			; 	( 
			; 		(newa (rpm (rest p) d a))
			; 	) 
			; 	; (format t "p= ~A || restp= ~A || d= ~A || rpm= ~A~%" p (rest p) d (rpm (rest p) d a) )
			; 	;See if we match 0 elements. 
			; 	;Note how this is accomplished. We advance
			; 	;p to the cdr of p in the recursion BUT DO
			; 	;NOT ADVANCE d.
			; 	;if so we return the new association list
			; 	(cond 
			; 		(newa newa)
			; 		; (t (format t "newa = ~A" newa))
			; 	;otherwise we try to match one data element
			; 		(t (rpm p (rest d) a))
			; 	)
			; )
			
		)

		;direct matching stuff
		;if it's a non-variable or match symbol,
		( (atom (first p)) 
			(cond
			 	;check to see if it is equal to the data
			 	;and recurse over the rest of p and d, if so
				( (eql (first p) (first d)) (rpm (rest p) (rest d) a) )
				;if it's not a match, fail
				(T NIL)
			)
		) 
		
		;& - carp here is the SUBLIST AMP CLAUSE
		( (is-amp (first p) ) 
			;so it is an amp-clause
			;print some debugging
		 	; (format t "(is-amp(~A) -> TRUE)~%(cdrcarp= ~A)~% " (car p) (rest (first p)) )
			(cond
			 	;call amped (below) on the first condition and the 
			 	;rest of the data set (the 1-1 gets handled there.)
			 	;if it's true, meaning conditionally matched, 
			 	;then go to the next element of p and d (past the &-clause)
				((amped (rest (first p)) d a) (rpm (rest p) (rest d) a))
				;there wasn't a conditional match. fail.
				(T nil)
			) 
		)
		
		;otherwise we failed
		;Now we know we have a list or something 
		;non-atomic/variable at the head of p
		; (T (format t "(first p = ~S || first d = ~S)~%" (first p) (first d) ))
		; (T (rpm (first p) (first d) a) )
		(T 
			(let ((newa (rpm (first p) (first d) a)) ) 
				;pattern match the sublists 
				(cond 
					;but it failed, so we fail
				 	((null newa) NIL) 
					;we succeeded, but newa could be "t" or
					;a binding list
					((listp newa) (rpm (rest p) (rest d) newa) ) 
					;Here newa is "t" so rpm with the association 
					;list of nil
					(T (rpm (rest p) (rest d) NIL) )
				)
			)
		)
	)
)

;p is the condition list part of the clause; 
;d is the whole rest of the data
(defun amped (p d a)
 	;degubbing
	; (format t "IN AMPED~%")
	; (format t "(p = ~A| carp = ~A | cdrp = ~A)~%" p (first p) (rest p))
	; (format t "PAST~%")
 	(cond
 	 	;if p is null, conditional list is exhausted
 	 	;maybe should be car p?
 	 	((null p) t) 
 	 	;if it's not exhausted run a pattern match on the 
 	 	;single element of d (have to only pass in the single,
 	 	;otherwise the rpm will be different lengths and it will fail)
 		;if it matches, keep checking the amp-clause
		((rpm (list (first p)) (list (first d)) a) (amped (rest p) d a))
		;if it doesn't rpm, the conditional is wrong.
		(T NIL)
 	)
)

(defun is-amp (x)
	(cond
	 	;gotta be a list, and the first part has to be an &
		((listp x) (equal '& (first x)))
		(T NIL)
	)
)


(defun is-lt (x) 
	(and 
	 	(not (listp x))
		(equal (elt (symbol-name x) 0) #\<)
		(>= (length (symbol-name x)) 2)
	)
)

(defun is-gt (x) 
	(and 
	 	(not (listp x))
		(equal (elt (symbol-name x) 0) #\>)
		(>= (length (symbol-name x)) 2)
	)
)

(defun is-vbl (x) 
	(and 
	 	(not (listp x))
		(equal (elt (symbol-name x) 0) #\?)
		(>= (length (symbol-name x)) 2)
	)
)

(defun is-not (x) 
	(and 
	 	(not (listp x))
		(equal (elt (symbol-name x) 0) #\!)
		(>= (length (symbol-name x)) 2)
	)
)

(defun bound ( x a )
 	(assoc x a)
)


;(defun assoc ( x a-list ) ( ... ) )

;boundp?

;* end-case?


