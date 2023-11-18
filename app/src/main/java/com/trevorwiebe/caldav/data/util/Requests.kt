package com.trevorwiebe.caldav.data.util

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

fun getCalendarRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<D:propfind xmlns:D=\"DAV:\">\n" +
                "  <D:allprop/>\n" +
                "</D:propfind>",
        depth = "1",
        method = "PROPFIND"
    )
}

fun getUserPrincipalsRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<d:propfind xmlns:d=\"DAV:\">\n" +
                "  <d:prop>\n" +
                "     <d:current-user-principal />\n" +
                "  </d:prop>\n" +
                "</d:propfind>",
        depth = "0",
        method = "PROPFIND"
    )
}

fun getCalendarLocationLinkRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<d:propfind xmlns:d=\"DAV:\" xmlns:c=\"urn:ietf:params:xml:ns:caldav\">\n" +
                "  <d:prop>\n" +
                "     <c:calendar-home-set />\n" +
                "  </d:prop>\n" +
                "</d:propfind>",
        depth = "0",
        method = "PROPFIND"
    )
}

fun getCalendarLinksRequest(): CalDavRequestBody {
    return CalDavRequestBody(
        body = "<d:propfind xmlns:d=\"DAV:\" xmlns:cs=\"http://calendarserver.org/ns/\" xmlns:c=\"urn:ietf:params:xml:ns:caldav\">\n" +
                "  <d:prop>\n" +
                "     <d:resourcetype />\n" +
                "     <d:displayname />\n" +
                "     <d:calendar-color/>\n" +
                "     <c:supported-calendar-component-set />\n" +
                "  </d:prop>\n" +
                "</d:propfind>",
        depth = "1",
        method = "PROPFIND"
    )
}