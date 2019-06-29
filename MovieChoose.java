package java_practice1;

import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.Raster;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.CharacterAttribute;

public class MovieChoose
        implements ActionListener, Runnable {
    // 标记
    private boolean flag = false;
    private JFrame randomFrame = new JFrame("看什么电影好呢？？？？");
    // 创建一个Panel对象。
    private JPanel randomPanel = new JPanel();
    private TextField tf = new TextField(30);
    private JCheckBox cheat = new JCheckBox("是否作弊？" , false);
    private JButton randomButton1 = new JButton("Run");
    private JButton randomButton2 = new JButton("Stop");
    private String[] name = { "哥斯拉（美国）", "禁闭岛（悬疑，美国）", "杀人回忆（犯罪，韩国）",
    		"隧道（韩国）", "流感（韩国）", "阿凡达", "恐怖分子", "摩天轮", "", "", "",};
    private String [] movieType = {"喜剧","爱情","动作","科幻","悬疑","惊悚","犯罪","动画"};
    private double rate = 0.0; 
    public MovieChoose() {
        // 向JPanel容器中添加三个组件
        randomPanel.add(tf);
        randomPanel.add(randomButton1);
        randomPanel.add(randomButton2);
        randomPanel.add(cheat);
        randomFrame.add(randomPanel);
        
        // 取得屏幕的宽度
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        // 取得屏幕的高度
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 设置组件不可改变大小
        randomFrame.setResizable(false);
        // 设置关闭窗体时结束程序
        randomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置组件大小
        randomFrame.setSize(400, 200);
        // 设置组件位置
        randomFrame.setLocation((width - 250) / 2, (height - 120) / 2);
        // 设置组件大小和位置
        // randomFrame.setBounds(30, 30, 250, 120);
        // 设置组件可见
        randomFrame.setVisible(true);
        addListener();
    }

    private void addListener() {
        randomButton1.addActionListener(this);
        randomButton2.addActionListener(this);
        //cheat.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if (obj == randomButton1) {
            synchronized (this) {
                notify();
                flag = true;
            }
        }
        if (obj == randomButton2) {
            synchronized (this) {
                flag = false;
            }
        }
//        if (obj == cheat) {
//            synchronized (this) {
//          
//            }
        
        
//        }
    }

    public void run() {
        int length = name.length;
        while (true) {
            try {
                if (!flag) {
                    synchronized (this) {
                    	if (cheat.isSelected())
                    		tf.setText(name[1]);
                    	this.wait();
                    
                    }
                }
                Random myRandomName = new Random();
                int RN = myRandomName.nextInt(length);
                tf.setText(name[RN]);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MovieChoose rn = new MovieChoose();
        Thread t1 = new Thread(rn);
        t1.start();
    }
}
