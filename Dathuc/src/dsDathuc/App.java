package dsDathuc;

import java.awt.Color;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class App extends JFrame {
    public App() {
        JFrame This = new JFrame();
        This.setTitle("Quản lý đa thức");
        This.setDefaultCloseOperation(3);
        This.setResizable(false);
        Border blackline;
        JPanel PnChinh = new JPanel();
        JPanel PnPhu = new JPanel();
        JPanel mainPanel = new JPanel();
        This.setSize(1000, 500);
        This.setLayout(new BorderLayout());
        blackline = BorderFactory.createLineBorder(Color.BLACK);
        PnChinh.setBorder(blackline);
        PnPhu.setBorder(blackline);
        This.add(mainPanel);
        PnChinh.setSize(1000, 300);
        PnPhu.setSize(1000, 200);
        This.add(PnChinh, BorderLayout.CENTER);
        This.add(PnPhu, BorderLayout.SOUTH);

        JLabel LbelKetqua = new JLabel("kết quả");
        JLabel LbelDathuc1 = new JLabel("kết quả");
        JLabel LbelDathuc2 = new JLabel("kết quả");
        JButton BtnCong = new JButton("Cộng");
        JButton BtnThugon = new JButton("Thu gọn đa thức");
        JButton BtnTru = new JButton("Trừ");
        JButton BtnNhan = new JButton("Nhân");
        JButton BtnDaoham = new JButton("Đạo hàm");
        List<JButton> btnList = new ArrayList<JButton>();
        btnList.add(BtnCong);
        btnList.add(BtnThugon);
        btnList.add(BtnTru);
        btnList.add(BtnNhan);
        btnList.add(BtnDaoham);
        for (JButton Btn : btnList) {
            PnPhu.add(Btn);
        }
        This.show();

    }

    public static void main(String[] args) throws Exception {
        App app = new App();
    }
}
