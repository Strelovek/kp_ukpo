package main;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;


class frmTest {
    /*================================================
    * UI tests
    ===============================================*/

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @org.junit.jupiter.api.Test
    public void parse_empty_arg() {
        FrameFixture window;
        frm frame;
        frame = GuiActionRunner.execute(() -> new frm());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        window.button("parse").click();
        Assert.assertEquals("Поля не заполнены!", window.textBox("answ").text());
        window.cleanUp();
    }

    @org.junit.jupiter.api.Test
    public void parse_empty_txt() {
        FrameFixture window;
        frm frame;
        frame = GuiActionRunner.execute(() -> new frm());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        window.textBox("regEx").enterText(".q");
        window.button("parse").click();
        Assert.assertEquals("Поля не заполнены!", window.textBox("answ").text());
        window.cleanUp();
    }

    @org.junit.jupiter.api.Test
    public void parse_empty_reg() {
        FrameFixture window;
        frm frame;
        frame = GuiActionRunner.execute(() -> new frm());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        window.textBox("txt").enterText("dfdnfgn");
        window.button("parse").click();
        Assert.assertEquals("Поля не заполнены!", window.textBox("answ").text());
        window.cleanUp();
    }

    @org.junit.jupiter.api.Test
    public void parse_correct_arg() {
        FrameFixture window;
        frm frame;
        frame = GuiActionRunner.execute(() -> new frm());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        window.textBox("regEx").enterText(".q");
        window.textBox("txt").enterText("aq");
        window.button("parse").click();
        Assert.assertEquals("0", window.textBox("answ").text());
        window.cleanUp();
    }

    @org.junit.jupiter.api.Test
    public void parse_err_reg() {
        FrameFixture window;
        frm frame;
        frame = GuiActionRunner.execute(() -> new frm());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        window.textBox("regEx").enterText("./");
        window.textBox("txt").enterText("aq");
        window.button("parse").click();
        Assert.assertEquals("совпадений не найдено!", window.textBox("answ").text());
        window.cleanUp();
    }

    @org.junit.jupiter.api.Test
    public void parse_txt_not_include_reg() {
        FrameFixture window;
        frm frame;
        frame = GuiActionRunner.execute(() -> new frm());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        window.textBox("regEx").enterText("a.w");
        window.textBox("txt").enterText("afgcfbq");
        window.button("parse").click();
        Assert.assertEquals("совпадений не найдено!", window.textBox("answ").text());
        window.cleanUp();
    }
}