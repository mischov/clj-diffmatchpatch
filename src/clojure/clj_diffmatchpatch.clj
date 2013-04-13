(ns clj-diffmatchpatch
  (:require [clj-diffmatchpatch.util :as u])
  (:import [google.dmp DiffMatchPatch]
           [google.dmp DiffMatchPatch$Diff]
           [google.dmp DiffMatchPatch$Operation]))

(defn diff
  ([text1 text2]
     (u/diffs-to-edn (.diff_main (DiffMatchPatch.) text1 text2))))

(defn cleanup-semantic
  [ediffs]
  (let [diffs (atom (u/edn-to-diffs ediffs))]
    (.diff_cleanupSemantic (DiffMatchPatch.) @diffs)
    (u/diffs-to-edn @diffs)))

(defn wdiff
  [text1 text2]
  (let [d (diff text1 text2)]
    (cleanup-semantic d)))

(defn pretty-html
  [ediffs]
  (let [diffs (u/edn-to-diffs ediffs)]
    (.diff_prettyHtml (DiffMatchPatch.) diffs)))

(defn patch-make
  [text1 cdiffs]
  (let [diffs (u/edn-to-diffs cdiffs)]
    (.patch_make (DiffMatchPatch.) text1 diffs)))

(defn patch-to-text
  [patches]
  (.patch_toText (DiffMatchPatch.) patches))

(defn patch-from-text
  [text]
  (.patch_fromText (DiffMatchPatch.) text))

(defn patch-apply
  [patches text1]
  (vec (.patch_apply (DiffMatchPatch.) patches text1)))
