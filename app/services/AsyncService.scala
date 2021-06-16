package services

import scala.concurrent.Future


import configuration.Contexts.{dbWriteOperationsContext, expensiveCpuOperationsContext, expensiveDbLookupsContext, simpleDbLookupsContext}



class AsyncService {
    def heavyComputational: Future[Int] = {
        Future {
            Thread.sleep(10000)
            1
        }(expensiveCpuOperationsContext)
    }
}
