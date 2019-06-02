package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frm extends JFrame {
    private JPanel root;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton parseButton;
    private JButton helpButton;
    private JButton btallfind;

    public frm(){
        this.getContentPane().add(root);
        this.btallfind.addActionListener(new MyButtonListener3());
        this.parseButton.addActionListener(new MyButtonListener());
        this.helpButton.addActionListener(new MyButtonListener2());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            btnclick();
        }
    }
    public class MyButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
           help();
        }
    }
    public class MyButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            btFindAllClick();
        }
    }
    void btnclick(){
        String reg = textArea1.getText();
        String src = textArea2.getText();
        if(!reg.isEmpty() && !src.isEmpty()) {
            src+='#';
            SimpleRegEx parse = new SimpleRegEx();
            int n=parse.indexOf(reg,src);
            if(n>=0){
                textArea3.setText(Integer.toString(n));
            }
            else{
                textArea3.setText("совпадений не найдено!");
            }
        }
        else {
            textArea3.setText("Поля не заполнены!");
        }

    }

    void help(){
        textArea3.setText("Результат работы парсера.");
        textArea1.setText("Поле для задания регулярного выражения. Правила создания выражения: \n   \".\" – любой символ\n" +
                "  \"*\" - 0 и более символов,\n" +
                "  \"+\" - 1 и более символов");
        textArea2.setText("Текст для поиска вставлять сюда.");
    }

    void btFindAllClick(){
        String reg = textArea1.getText();
        String src = textArea2.getText();
        src+='#';
        SimpleRegEx parse = new SimpleRegEx();
        textArea3.setText(parse.findAll(reg,src));
    }
}
