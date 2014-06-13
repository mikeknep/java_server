# Dahomey

**An HTTP server written in Java.**

When running the server via `java -jar`, an external application **must** be provided via an "-a" flag in the command line. Example:

`java -jar dahomey.jar -a router.jar`

You may also optionally define a port with "-p" and a directory to serve with "-d". Example:

`java -jar dahomey.jar -p 5000 -d /Users/mrk/Desktop -a /Users/mrk/somewhere_else/router.jar`

Without these optional flags, the server defaults to port 1961, and the "public/" directory (note the relative path for "public/").
