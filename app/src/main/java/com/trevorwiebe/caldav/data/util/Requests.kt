package com.trevorwiebe.caldav.data.util

import com.trevorwiebe.caldav.data.model.CalDavRequestBody

fun displayNameRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<d:propfind xmlns:d=\"DAV:\" xmlns:cs=\"https://calendar.mercyh.org\">\n" +
                "  <d:prop>\n" +
                "     <d:displayname />\n" +
                "  </d:prop>\n" +
                "</d:propfind>",
        url = "calendars/testing/",
        depth = "0"
    )
}

fun availableCalendarsRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<d:propfind xmlns:d=\"DAV:\" xmlns:cs=\"https://calendar.mercyh.org\">\n" +
                "</d:propfind>",
        url = "calendars/testing/",
        depth = "1"
    )
}