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
    private JButton stressTestButton;

    public frm(){
        this.getContentPane().add(root);
        this.parseButton.addActionListener(new MyButtonListener());
        this.stressTestButton.addActionListener(new MyButtonListener2());
        parseButton.setName("parse");
        stressTestButton.setName("streesTest");
        textArea3.setName("answ");
        textArea1.setName("regEx");
        textArea2.setName("txt");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            btnclick();
        }
    }
    public class MyButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            stressTest();
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

    void stressTest(){
            String reg = "c*a.b+";
            String src = "cawbbb#";
            SimpleRegEx parse = new SimpleRegEx();;
        for(int i =0 ;i<=9000;i++){
            System.out.println(i);
                parse.indexOf(reg,src);
        }
        System.out.print("Done");
    }
}
