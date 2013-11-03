(defun myMin (x)
 (cond
  ((null (rest x)) (first x))
  ((> (first x) (first(rest x))) (myMin (rest x)))
  (t (myMin (cons (first x) (rest (rest x))))) 
 )
)