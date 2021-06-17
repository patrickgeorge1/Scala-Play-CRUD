# HOW TO RUN

```bash
$ sbt
[project_name] $ run
```


## connect to sql database

Shows how to work with a MySQL database

## async

See Routes -> AsyncController -> AsyncService flow to see how to work async.
Mention that multiple ExecutionContexts (ThreadPools) were created in application.conf and invaked in configuration/Contexts

## filtering with Actions / Middlewares

Created base action "FilterableAction" (Regular Action + map) and "Filters" that can be applied to it

Go and see FilteredController

