import java.awt.*;
import javax.swing.*;

//首先实现登录界面
public class Login {

    //主函数入口
    public static void main(String args[]) {
        Login l = new Login();
        l.showUI();
    }

    public void showUI() {
        javax.swing.JFrame jf = new javax.swing.JFrame();
        jf.setTitle("启动界面");
        jf.setSize(340, 500);//只对顶级容器有效
        jf.setDefaultCloseOperation(3);//窗体关闭时结束程序
        jf.setLocationRelativeTo(null);//居中
        jf.setResizable(false);

        //选择布局类型，定义流式布局的对象,并且设置每个组件之间相隔5cm
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 5, 5);
        jf.setLayout(fl);//设置顶级容器的布局为流式布局

        //设置格式大小
        Dimension dim1 = new Dimension(340, 300);//图片大小
        Dimension dim2 = new Dimension(100, 20);//标签的大小
        Dimension dim3 = new Dimension(250, 30);//输入框的大小
        Dimension dim4 = new Dimension(100, 40);//按钮的大小

        //添加组件
        //添加图片，先要把图片加载到内存中。利用IamgeIcon类把图片加载到内存
        ImageIcon icon = new ImageIcon("./timg.jpg");
        JLabel labpic = new JLabel(icon);//不可以直接把图片加到顶级容器中，需要先将其设置为标签
        labpic.setPreferredSize(dim1);
        jf.add(labpic);//再将标签加到顶级容器中

        //添加“账号”标签
        JLabel labname = new JLabel();
        labname.setText("客户端IP地址：");
        labname.setPreferredSize(dim2);
        jf.add(labname);

        //添加账号输入框，并添加监控事件
        JTextField ip = new JTextField();
        ip.setPreferredSize(dim3);
        jf.add(ip);

        //添加一个button按钮
        JButton button1 = new JButton();
        button1.setText("启动服务端");
        button1.setPreferredSize(dim4);
        jf.add(button1);

        JButton button2=new JButton();
        button2.setText("启动客户端");
        button2.setPreferredSize(dim4);
        jf.add(button2);

        jf.setVisible(true);

        //首先实例化登录按钮监听类的对象，并把登录界面中账号和密码输入框的对象传给它
        UIListener l1 = new UIListener(jf, ip,1);
        //对当前窗体添加监听方法
        button1.addActionListener(l1);//监控按钮

        UIListener l2 = new UIListener(jf, ip,2);
        //对当前窗体添加监听方法
        button2.addActionListener(l2);//监控按钮
    }
}
