package com.vgen.htmlutils.htmlformatter;

import java.util.Map;

/**
 * Utility class intended to simplify the inlining of HTML within java source code.
 */
public class HtmlUtils {

    private ContentFormatter formatter = new ContentFormatter();

    /**
     * Creates a section.
     *
     * @param content The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     */
    public String divider(String content, Map<String, String> attributes) {
        return formatter.htmlFormat(content, "div", attributes);
    }

    /**
     * Creates a heading.
     *
     * @param content The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @param size The size of the heading to be created.
     * @return
     * @throws IllegalArgumentException
     */
    public String heading(String content, Map<String, String> attributes, int size) throws IllegalArgumentException {
        if(size <= 5) {
            return formatter.htmlFormat(content, "h" + size, attributes);
        } else {
            throw new IllegalArgumentException("Heading size must be between 1 and 5");
        }
    }

    /**
     * Creates a paragraph.
     *
     * @param content The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     */
    public String paragraph(String content, Map<String, String> attributes) {
        return formatter.htmlFormat(content, "p", attributes);
    }

}
