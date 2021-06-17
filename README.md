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

Created action builder "FilterAction" that transform (Request => Request + hashMap) and "Filters" that can be applied to it

Go and see FilteredController


## testing

Created test/services/UserServiceSpecTest where I mocked userRepository and instructed the mock how to return when is called