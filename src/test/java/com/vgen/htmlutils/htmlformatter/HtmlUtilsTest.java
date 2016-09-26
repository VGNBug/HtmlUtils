package com.vgen.htmlutils.htmlformatter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HtmlUtilsTest {

    private HtmlUtils utils;
    private final String TEXT = "Hello world!";
    public static final String URL = "https://www.example.com";
    private Map<HtmlAttribute, String> attribsWithClass = new HashMap<>();
    private Map<HtmlAttribute, String> attribsWithHref = new HashMap<>();

    @Before
    public void setup() {
        utils = new HtmlUtils();
        attribsWithClass.put(HtmlAttribute.CLASS, "test");
        attribsWithHref.put(HtmlAttribute.HREF, URL);
    }

    @Test
    public void testDividerNoAttributesShouldSucceed() {
        final String actual = testDivider(null);

        assertNotNull(actual);
        assertEquals("<div>" + TEXT + "</div>", actual);
    }

    @Test
    public void testDividerWithClassAttributeShouldSucceed() {
        final String actual = testDivider(attribsWithClass);

        assertNotNull(actual);
        assertEquals("<div class=\"test\">" + TEXT + "</div>", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDividerWithHrefAttributeShouldFail() {
        assertNull(testDivider(attribsWithHref));
    }

    @Test
    public void testFooterWithNoAttributesShouldSucceed() {
        final String actual = utils.footer(TEXT, null);

        assertNotNull(actual);
        assertEquals("<footer>" + TEXT + "</footer>", actual);
    }

    @Test
    public void testFooterWithClassAttributeShouldSucceed() {
        final String actual = utils.footer(TEXT, attribsWithClass);

        assertNotNull(actual);
        assertEquals("<footer class=\"test\">" + TEXT + "</footer>", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFooterWithHrefAttributeShouldFail() {
        assertNull(utils.footer(TEXT, attribsWithHref));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeadingSize0ShouldFail() {
        testHeadingForFailure(0, null);
    }

    @Test
    public void testHeadingSize1ShouldSucceed() {
        testHeadingForSuccess(1, null, "<h1>" + TEXT + "</h1>");
    }

    @Test
    public void testHeadingSize2ShouldSucceed() {
        testHeadingForSuccess(2, null, "<h2>" + TEXT + "</h2>");
    }

    @Test
    public void testHeadingSize3ShouldSucceed() {
        testHeadingForSuccess(3, null, "<h3>" + TEXT + "</h3>");
    }

    @Test
    public void testHeadingSize4ShouldSucceed() {

        testHeadingForSuccess(4, null, "<h4>" + TEXT + "</h4>");
    }

    @Test
    public void testHeadingSize5ShouldSucceed() {
        testHeadingForSuccess(5, null, "<h5>" + TEXT + "</h5>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeadingSize6ShouldFail() {
        testHeadingForFailure(6, null);
    }

    @Test
    public void testHeadingWithClassAttributeShouldSucceed() {
        testHeadingForSuccess(1, attribsWithClass, "<h1 class=\"test\">" + TEXT + "</h1>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeadingWithHrefAttributeShouldFail() {
        testHeadingForFailure(1, attribsWithHref);
    }

    @Test
    public void testLink() {
        final String actual = utils.link(TEXT, URL, null);

        assertNotNull(actual);
        assertEquals("<a href=\"" + URL + "\">" + TEXT + "</a>", actual);
    }

    @Test
    public void testLinkWithClassShouldSucceed() {
        Map<HtmlAttribute, String> attribs = attribsWithHref;
        attribs.put(HtmlAttribute.CLASS, "test");

        final String actual = utils.link(TEXT, URL, attribs);

        assertNotNull(actual);
        assertEquals("<a class=\"test\" href=\"" + URL + "\">" + TEXT + "</a>", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkNoUrlShouldFail() {
        assertNull(utils.link(TEXT, null, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkNoLabelShouldFail() {
        assertNull(utils.link(null, URL, null));
    }

    @Test
    public void testParagraph() {
        final String actual = utils.paragraph(TEXT, null);

        assertNotNull(actual);
        assertEquals("<p>" + TEXT + "</p>", actual);
    }

    private String testDivider(Map<HtmlAttribute, String> attributes) {
        return utils.divider(TEXT, attributes);
    }

    private void testHeadingForSuccess(int size, Map<HtmlAttribute, String> attributes, String expected) {
        final String actual = utils.heading(TEXT, attributes, size);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    private void testHeadingForFailure(int size, Map<HtmlAttribute, String> attributes) {
        final String actual = utils.heading(TEXT, attributes, size);

        assertNull(actual);
    }

}
