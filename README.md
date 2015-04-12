# re-frame

A Leiningen template for the setup that I like to use for a Clojure
web app.  Uses ring/compojure on the back-end and re-frame/secretary
on the front-end. Also has configuration for a figwheel dev
environment, and deployment to Heroku.

## Usage

For development, start two servers (in separate shells), figwheel and
ring.

```shell
rlwrap lein figwheel
lein ring server
```

## License

Copyright © 2015 Geoff Shannon

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
