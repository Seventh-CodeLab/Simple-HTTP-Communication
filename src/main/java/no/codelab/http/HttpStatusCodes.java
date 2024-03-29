package no.codelab.http;

import java.util.HashMap;
import java.util.Map;

class HttpStatusCodes {
  static Map<Integer,String> statusCodeList = new HashMap<>();

  static {
    statusCodeList.put(100, "Continue");
    statusCodeList.put(101, "Switching Protocols");
    statusCodeList.put(102, "Processing (WebDAV)");
    statusCodeList.put(200, "OK");
    statusCodeList.put(201, "Created");
    statusCodeList.put(202, "Accepted");
    statusCodeList.put(203, "Non-Authorative Information");
    statusCodeList.put(204, "No Content");
    statusCodeList.put(205, "Reset Content");
    statusCodeList.put(206, "Partial Content");
    statusCodeList.put(207, "Multi-Status (WebDAV)");
    statusCodeList.put(208, "Already Reported (WebDAV)");
    statusCodeList.put(226, "IM Used");
    statusCodeList.put(300, "Multiple Choices");
    statusCodeList.put(301, "Moved Permanently");
    statusCodeList.put(302, "Found");
    statusCodeList.put(303, "See Other");
    statusCodeList.put(304, "Not Modified");
    statusCodeList.put(305, "Use Proxy");
    statusCodeList.put(306, "(Unused)");
    statusCodeList.put(307, "Temporary Redirect");
    statusCodeList.put(308, "Permanent Redirect (Experimental)");
    statusCodeList.put(400, "Bad Request");
    statusCodeList.put(401, "Unauthorized");
    statusCodeList.put(402, "Payment Required");
    statusCodeList.put(403, "Forbidden");
    statusCodeList.put(404, "Not Found");
    statusCodeList.put(405, "Method Not Allowed");
    statusCodeList.put(406, "Not Acceptable");
    statusCodeList.put(407, "Proxy Authentication Required");
    statusCodeList.put(408, "Request Timeout");
    statusCodeList.put(409, "Conflict");
    statusCodeList.put(410, "Gone");
    statusCodeList.put(411, "Length Required");
    statusCodeList.put(412, "Precondition Failed");
    statusCodeList.put(413, "Request Entity Too Large");
    statusCodeList.put(414, "Request-URI Too Long");
    statusCodeList.put(415, "Unsupported Media Type");
    statusCodeList.put(416, "Requested Range Not Satisfiable");
    statusCodeList.put(417, "Expectation Failed");
    statusCodeList.put(418, "I'm a teapot (RFC 2324)");
    statusCodeList.put(420, "Enhance Your Calm (Twitter)");
    statusCodeList.put(422, "Unprocessable Entity (WebDAV)");
    statusCodeList.put(423, "Locked (WebDAV)");
    statusCodeList.put(424, "Failed Dependency (WebDAV)");
    statusCodeList.put(425, "Reserved for WebDAV");
    statusCodeList.put(426, "Upgrade Required");
    statusCodeList.put(428, "Precondition Required");
    statusCodeList.put(429, "Too Many Requests");
    statusCodeList.put(431, "Request Header Fields Too Large");
    statusCodeList.put(444, "No Response (Nginx)");
    statusCodeList.put(449, "Retry With (Microsoft)");
    statusCodeList.put(450, "Blocked by Windows Parental Controls (Microsoft)");
    statusCodeList.put(451, "Unavailable For Legal Reasons");
    statusCodeList.put(499, "Client Closed Request (Nginx)");
    statusCodeList.put(500, "Internal Server Error");
    statusCodeList.put(501, "Not Implemented");
    statusCodeList.put(502, "Bad Gateway");
    statusCodeList.put(503, "Service Unavailable");
    statusCodeList.put(504, "Gateway Timeout");
    statusCodeList.put(505, "HTTP Version Not Supported");
    statusCodeList.put(506, "Variant Also Negotiates (Experimental)");
    statusCodeList.put(507, "Insufficient Storage (WebDAV)");
    statusCodeList.put(508, "Loop Detected (WebDAV)");
    statusCodeList.put(509, "Bandwidth Limit Exceeded (Apache)");
    statusCodeList.put(510, "Not Extended");
    statusCodeList.put(511, "Network Authentication Required");
    statusCodeList.put(598, "Network read timeout error");
    statusCodeList.put(599, "Network connect timeout error");
  }
}

