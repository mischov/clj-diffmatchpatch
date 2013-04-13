(ns clj-diffmatchpatch.util
  (:import [google.dmp DiffMatchPatch]
           [google.dmp DiffMatchPatch$Diff DiffMatchPatch$Operation]))

(defn diff-to-edn
  [diff]
  (let [text (.text diff)
        operation (str (.operation diff))
        op (cond
            (= operation "INSERT") :insert
            (= operation "EQUAL") :equal
            (= operation "DELETE") :delete)]
    [op, text]))

(defn diffs-to-edn
  [diffs]
  (map diff-to-edn diffs))

(defn edn-to-diff
  [edn-diff]
  (let [op (nth edn-diff 0)
        operation (cond
                   (= op :insert) (DiffMatchPatch$Operation/INSERT)
                   (= op :equal) (DiffMatchPatch$Operation/EQUAL)
                   (= op :delete) (DiffMatchPatch$Operation/DELETE))
        text (nth edn-diff 1)]
    (DiffMatchPatch$Diff. operation text)))

(defn edn-to-diffs
  [edn-diffs]
  (java.util.LinkedList. (map edn-to-diff edn-diffs)))
