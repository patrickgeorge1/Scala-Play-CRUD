package configuration

import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext


object Contexts {
     val actorSystem: ActorSystem = ActorSystem()

     val simpleDbLookupsContext: ExecutionContext = actorSystem.dispatchers.lookup("contexts.simple-db-lookups")
     val expensiveDbLookupsContext: ExecutionContext =
        actorSystem.dispatchers.lookup("contexts.expensive-db-lookups")
     val dbWriteOperationsContext: ExecutionContext = actorSystem.dispatchers.lookup("contexts.db-write-operations")
     val expensiveCpuOperationsContext: ExecutionContext =
        actorSystem.dispatchers.lookup("contexts.expensive-cpu-operations")
}