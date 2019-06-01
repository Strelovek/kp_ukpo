package main;
import org.junit.Assert;

class SimpleRegExTest {

    /*================================================
     * black box tests
     ===============================================*/

    SimpleRegEx t = new SimpleRegEx();
    String regex;
    String txt;

    @org.junit.jupiter.api.Test
    void indexOf_incorrect_input_arguments() {
        regex = ".+.";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = "";
        txt = "fdgfd";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = "";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

    }

    @org.junit.jupiter.api.Test
    void indexOf_include_regEx() {
        regex = "aQ";
        txt = "aQ";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = "aQ";
        txt = " aQ";
        txt += "#";
        Assert.assertEquals(1, t.indexOf(regex, txt));

        regex = "aQ";
        txt = " aQ ";
        txt += "#";
        Assert.assertEquals(1, t.indexOf(regex, txt));

        regex = "aQ";
        txt = "sdfs aQ";
        txt += "#";
        Assert.assertEquals(5, t.indexOf(regex, txt));

        regex = "aQ";
        txt = "gfhfh aQ fghfgf";
        txt += "#";
        Assert.assertEquals(6, t.indexOf(regex, txt));
    }

    @org.junit.jupiter.api.Test
    void indexOf_not_include_regEx() {
        regex = "aQ";
        txt = "dgd fgdfg";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = "aQ";
        txt = "       ";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = ".*";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = ".";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = ".+t";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));
    }

    /*================================================
     * white box tests
     ===============================================*/
    @org.junit.jupiter.api.Test
    void indexOf_all_symb() {
        regex = ".";
        txt = "dgd fgdfg";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = ".";
        txt = "       ";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = "a.";
        txt = "aq";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = "a.";
        txt = " aq";
        txt += "#";
        Assert.assertEquals(1, t.indexOf(regex, txt));
    }

    @org.junit.jupiter.api.Test
    void indexOf_zero_or_more() {
        regex = ".*";
        txt = "dgd fgdfg";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = ".*";
        txt = "       ";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        /*regex = "be*";
        txt = "b beeeee";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));*/
    }

    @org.junit.jupiter.api.Test
    void indexOf_one_or_more() {
        regex = ".+";
        txt = "dgd fgdfg";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = ".+";
        txt = "       ";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = "be+";
        txt = "b be";
        txt += "#";
        Assert.assertEquals(2, t.indexOf(regex, txt));

        regex = "be+";
        txt = "b beeeee";
        txt += "#";
        Assert.assertEquals(2, t.indexOf(regex, txt));
    }

    @org.junit.jupiter.api.Test
    void indexOf_combo() {
        regex = "c*a.b+";
        txt = "cawbbb";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = "a.b+";
        txt = "awb";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

        regex = "b*.";
        txt = "abbq";
        txt += "#";
        Assert.assertEquals(1, t.indexOf(regex, txt));

        regex = ".+.";
        txt = "arw";
        txt += "#";
        Assert.assertEquals(0, t.indexOf(regex, txt));

    }

    @org.junit.jupiter.api.Test
    void check_specific_incorrect_arguments() {
        regex = "./";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = ".q/";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = ".+/";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = "/";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));

        regex = "a...*/";
        txt = "";
        txt += "#";
        Assert.assertEquals(-1, t.indexOf(regex, txt));
    }

}