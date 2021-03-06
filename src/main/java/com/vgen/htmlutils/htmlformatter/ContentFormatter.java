package com.vgen.htmlutils.htmlformatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Formatter class, which contains infrastructure for generating HTML.
 */
class ContentFormatter {

    private final Log LOGGER = LogFactory.getLog(this.getClass());

    public String htmlFormat(String content, String tag, Map<HtmlAttribute, String> attributes) {

        String output = "";

        if (!"".equals(tag)) {
            output += formatOpenTag(tag, attributes) + content + formatCloseTag(tag);
        } else {
            throw new IllegalArgumentException("tag attribute cannot be null");
        }

        return output;
    }

    private String formatOpenTag(String tag, Map<HtmlAttribute, String> attributes) {

        String output = "";

        if (!"".equals(tag)) {
            output += "<" + tag;

            if(attributes != null && attributes.size() > 0) {
                for (Map.Entry<HtmlAttribute, String> entry : attributes.entrySet()) {
                    output += " " + entry.getKey().getFriendlyName() + "=\"" + entry.getValue() + "\"";
                }
            }

            output += ">";

        } else {
            checkStringEmptyOrNull("tag", tag);
        }

        return output;
    }

    private String formatCloseTag(String tag) {

        String output = "";

        if (!"".equals(tag)) {
            output += "</" + tag + ">";
        } else {
            checkStringEmptyOrNull("tag", tag);
        }

        return output;
    }

    private IllegalArgumentException checkStringEmptyOrNull(String stringName, String stringValue) {

        String error;

        if (stringValue == null) {
            error = stringName + " was null";
        } else {
            error = stringName + " was empty";
        }

        LOGGER.error(error);
        throw new IllegalArgumentException(error);
    }

}
