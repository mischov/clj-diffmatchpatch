# clj-diffmatchpatch

A Clojure wrapper for the Java implimentation of google-diff-match-patch by Neil Fraser.

```clojure
[clj-diffmatchpatch "0.0.9.3"]
```

## Usage

```clojure
(require '[clj-diffmatchpatch :as dmp])
(dmp/wdiff "I am an example." "I am a simple example.") 
```

## Status

clj-diffmatchpatch is currently in alpha.

1. From the google-diff-match-patch api, currently implemented wrapper functions: 
```
diff cleanup-semantic pretty-html patch-make patch-to-text patch-from-text patch-apply
```

1. Original api functions currently updated to take and output edn instead of java objects / structures: 
```
diff cleanup-semantic
```

1. Original api functions currently updated to take edn: 
```
patch-make
```

1. Convienience function added: 
```
wdiff
```


## License

Distributed under the Apache 2.0 license.
