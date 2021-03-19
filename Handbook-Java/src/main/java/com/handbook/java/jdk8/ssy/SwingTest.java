package com.handbook.java.jdk8.ssy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame("Test");
        JButton jButton=new JButton("点击");
//        jButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.err.println("Button Pressed !");
//            }
//        });
        jButton.addActionListener(e -> System.err.println("Button Pressed !"));
        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
