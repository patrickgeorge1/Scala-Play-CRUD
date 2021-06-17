package controllers

import play.api.mvc.{Action, _}

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import filters.FilterAction
import filters.Filters


class FilteredController  @Inject()(cc: MessagesControllerComponents,
                                    filterAction: FilterAction,
                                    filters: Filters)
                                   (implicit ec: ExecutionContext) extends MessagesAbstractController(cc)
{

    // specify which filters to apply
    def index: Action[AnyContent] = filterAction
        .andThen(filters.AddDateAndTimeFilter)
        .andThen(filters.AddBullshitFilter) {
            request =>
                Ok(request.filteredData.toString())
    }

}
