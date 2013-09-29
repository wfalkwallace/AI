(defun match(p d) ;p:pattern; d:data
 (cond
  ((and (null (car p)) (null (car d))) t)
  ((equal (car p)(car d)) (match (cdr p)(cdr d)))
  (T `NIL)
  )
 )
 
 




a = (1 2 3 4 5)
(car a) --> 1   ;head
(cdr a) --> (2 3 4 5)	;rest of list
(cond
 ((condition1) .... )
 ((condition2) .... )
 ((condition3) .... )
 (T (.....))
 )


SO:

(match (ai cs)(ai cs)) ---> error because looks for func ai
instead: (match '(ai cs)'(ai cs))

or (match (cons ai (cons cs nil))) also bad --> need (match (cons 'ai (cons 'cs nil)))

=> quote op is like a "dont look up"

