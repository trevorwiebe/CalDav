package com.trevorwiebe.caldav.domain.usecases.discover_cal

import com.trevorwiebe.caldav.domain.model.AuthCalendarModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

@OptIn(ExperimentalCoroutinesApi::class)
class LoadAvailableCalendars(
    private val getUserPrincipals: GetUserPrincipals,
    private val getCalendarLocationLink: GetCalendarLocationLink,
    private val getAuthCalendars: GetAuthCalendars
) {

    suspend operator fun invoke(
        username:String, password: String, baseUrl: String
    ): Flow<List<AuthCalendarModel>> {

        var currentUrl = baseUrl

        return getUserPrincipals(username, password, currentUrl)
            .flatMapConcat { principalsList ->
                currentUrl = currentUrl.replace(principalsList[0], principalsList[1])
                getCalendarLocationLink(username, password, currentUrl)
            }
            .flatMapConcat { baseCalLinks ->
                currentUrl = currentUrl.replace(baseCalLinks[0], baseCalLinks[1])
                getAuthCalendars(username, password, currentUrl)
            }
    }

}