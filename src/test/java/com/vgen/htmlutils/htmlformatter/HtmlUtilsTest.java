package com.vgen.htmlutils.htmlformatter;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HtmlUtilsTest {

    private HtmlUtils utils;
    final String TEXT = "Hello world!";

    @Before
    public void setup() {
        utils = new HtmlUtils();
    }

    @Test
    public void testDivider() {
        final String actual = utils.divider(TEXT, null);

        assertNotNull(actual);
        assertEquals("<div>" + TEXT + "</div>", actual);
    }

    @Test
    public void testFooter() {
        final String actual = utils.footer(TEXT, null);

        assertNotNull(actual);
        assertEquals("<footer>" + TEXT + "</footer>", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeadingSize0ShouldFail() {
        testHeadingForFailure(0);
    }

    @Test
    public void testHeadingSize1ShouldSucceed() {
        testHeadingForSuccess(1);
    }

    @Test
    public void testHeadingSize2ShouldSucceed() {
        testHeadingForSuccess(2);
    }

    @Test
    public void testHeadingSize3ShouldSucceed() {
        testHeadingForSuccess(3);
    }

    @Test
    public void testHeadingSize4ShouldSucceed() {
        testHeadingForSuccess(4);
    }

    @Test
    public void testHeadingSize5ShouldSucceed() {
        testHeadingForSuccess(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeadingSize6ShouldFail() {
        testHeadingForFailure(6);
    }

    @Test
    public void testLink() {
        final String url = "https://www.example.com";
        final String actual = utils.link(TEXT, url, null);

        assertNotNull(actual);
        assertEquals("<a href=\"" + url + "\">" + TEXT + "</a>", actual);
    }

    @Test
    public void testParagraph() {
        final String actual = utils.paragraph(TEXT, null);

        assertNotNull(actual);
        assertEquals("<p>" + TEXT + "</p>", actual);
    }

    private void testHeadingForSuccess(int size) {
        final String actual = utils.heading(TEXT, null, size);

        assertNotNull(actual);
        assertEquals("<h" + size + ">" + TEXT + "</h" + size + ">", actual);
    }

    private void testHeadingForFailure(int size) {
        final String actual = utils.heading(TEXT, null, size);

        assertNull(actual);
    }

}
