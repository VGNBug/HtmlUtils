package com.vgen.htmlutils.htmlformatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class intended to simplify the inlining of HTML within java source code.
 */
public class HtmlUtils {

    private ContentFormatter formatter = new ContentFormatter();
    private List<HtmlAttribute> commonExclusions = new ArrayList<>();

    public HtmlUtils() {
        commonExclusions.add(HtmlAttribute.HREF);
    }

    /**
     * Creates a section.
     *
     * @param content    The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     * @throws IllegalArgumentException if illegal attributes are supplied to this element.
     */
    public String divider(String content, Map<HtmlAttribute, String> attributes) throws IllegalArgumentException {

        final String tag = "div";

        if(!includesExcludedAttributes(attributes, commonExclusions, tag)) {
            return formatter.htmlFormat(content, tag, attributes);
        } else {
            throw new IllegalArgumentException("Illegal attributes supplied to this element");
        }
    }

    /**
     * Creates a footer.
     *
     * @param content    The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     * @throws IllegalArgumentException if illegal attributes are supplied to this element.
     */
    public String footer(String content, Map<HtmlAttribute, String> attributes) throws IllegalArgumentException {
        final String tag = "footer";

        if(! includesExcludedAttributes(attributes, commonExclusions, tag)) {
            return formatter.htmlFormat(content, tag, attributes);
        } else {
            throw new IllegalArgumentException("Illegal attributes supplied to this element");
        }
    }

    /**
     * Creates a heading.
     *
     * @param content    The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @param size       The size of the heading to be created, between 1 and 5.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     * @throws IllegalArgumentException if an invalid heading size is supplied.
     */
    public String heading(String content, Map<HtmlAttribute, String> attributes, int size) throws IllegalArgumentException {
        if (size >= 1 && size <= 5) {
            return formatter.htmlFormat(content, "h" + size, attributes);
        } else {
            throw new IllegalArgumentException("Heading size must be between 1 and 5");
        }
    }

    /**
     * Creates a clickable link.
     *
     * @param text       The label text for the link.
     * @param url        The URL to which the link will direct the browser.
     * @param attributes Any attributes to be added to the tag.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     * @throws IllegalArgumentException if either text or url is null.
     */
    public String link(String text, String url, Map<HtmlAttribute, String> attributes) {

        if (!"".equals(text) && !"".equals(url)) {
            if (attributes == null) {
                attributes = new HashMap<>();
            }

            attributes.put(HtmlAttribute.HREF, url);
            return formatter.htmlFormat(text, "a", attributes);

        } else if (!"".equals(text) && "".equals(url)) {
            throw new IllegalArgumentException("A URL must be supplied for a link to be produced.");
        } else if ("".equals(text) && !"".equals(url)) {
            throw new IllegalArgumentException("A label must be supplied for a link to be produced");
        } else {
            throw new IllegalArgumentException("Both a label and a URL must be supplied for a link to be produced");
        }
    }

    /**
     * Creates a paragraph.
     *
     * @param content    The content to be wrapped.
     * @param attributes Any attributes to be added to the tag.
     * @return The wrapped content, with any attributes applied inside the opening tag.
     */
    public String paragraph(String content, Map<HtmlAttribute, String> attributes) {
        return formatter.htmlFormat(content, "p", attributes);
    }

    private boolean includesExcludedAttributes(Map<HtmlAttribute, String> attributes, List<HtmlAttribute> exclusions, String callersTag) {

        boolean doesIncludeExcludedAttributes = false;

        if(attributes != null && exclusions != null) {
            for (Map.Entry<HtmlAttribute, String> attribute : attributes.entrySet()) {
                for (HtmlAttribute exclusion : exclusions) {
                    if (attribute.getKey().getFriendlyName().equals(exclusion.getFriendlyName())) {
                        throw new IllegalArgumentException(attribute.getKey().getFriendlyName() +
                                " is not an acceptable attribute for a <" + callersTag + "> element");
                    }
                }
            }
        }

        return doesIncludeExcludedAttributes;
    }
}
