/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class MsgBox {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component perent, String mess) {
        int result = JOptionPane.showConfirmDialog(perent, mess, "Hệ thống quản lý đào tạo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String promt(Component parent, String mess) {
        return JOptionPane.showInputDialog(parent, mess, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }
}
