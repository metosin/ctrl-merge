# Ctrl-Merge

This is a standalone and enhanced implementation of the [Leiningen][] `meta-merge`
function, forked from [Meta-Merge][]. It's particularly useful for merging configuration maps.

[Leiningen]: https://github.com/technomancy/leiningen
[Meta-Merge]: https://github.com/weavejester/meta-merge

## Installation

To install, add the following to your project `:dependencies`:

    [metosin/ctrl-merge "1.0.0"]

## Usage

The `merge` function recursively merges two data structures.

```clojure
(require '[ctrl-merge.core :as cm])

(cm/merge {:a [:b :c]} {:a [:d]})
=> {:a [:b :c :d]}
```

## Metadata

Metadata hints can be provided to override the default behavior:

### Prepend

When `merge` is called with a hash-map that has `^:prepend` on it, the
function  will take the values on the right and prepend them to the set on the
left (the default would **append** them as you can see above). You can see this
below.

```clojure
(cm/merge {:a [:b :c]} {:a ^:prepend [:d]})
=> {:a [:d :b :c]}
```

### Replace

When `merge` is called with a hash-map that has `^:replace` on it, the
function will take the values on the right and completely replace the ones on
the left. You can see this below:

```clojure
(cm/merge {:a [:b :c]} {:a ^:replace [:d]})
=> {:a [:d]}
```

With replace the map to the right takes precedence.

### Displace

When `merge` is called with a hash-map that has `^:displace` on it, the
function will take the values on the right, only if there are no values on the
left. You can see this below:

```clojure
(cm/merge {:a [:b :c]} {:a ^:displace [:d]})
=> {:a [:b :c]}
```

With displace the map to the left takes precedence. This makes displace a
good solution for default values.

## License

Copyright © 2023 Phil Hagelberg, James Reeves, Metosin and all the Leiningen
[contributors][].

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[contributors]: https://github.com/technomancy/leiningen/graphs/contributors
