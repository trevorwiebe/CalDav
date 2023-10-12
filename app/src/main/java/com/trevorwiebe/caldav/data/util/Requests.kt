package com.trevorwiebe.caldav.data.util

import com.trevorwiebe.caldav.data.model.CalDavRequestBody

fun getEventsRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<c:calendar-query xmlns:d=\"DAV:\" xmlns:c=\"urn:ietf:params:xml:ns:caldav\">\n" +
                "    <d:prop>\n" +
                "        <d:getetag />\n" +
                "        <c:calendar-data />\n" +
                "    </d:prop>\n" +
                "    <c:filter>\n" +
                "        <c:comp-filter name=\"VCALENDAR\" />\n" +
                "    </c:filter>\n" +
                "</c:calendar-query>",
        depth = "1",
        method = "REPORT"
    )
}