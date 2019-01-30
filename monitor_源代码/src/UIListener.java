import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UIListener implements ActionListener {
    private JTextField ip;//账号输入框对象
    private JPasswordField jp;//密码输入框对象
//    private JFrame login;//定义一个窗体对象
    private JFrame client;
    private int flag;

    public UIListener(JFrame login, JTextField ip,int flag) {
//        this.login = login;//获取登录界面
        this.ip = ip;//获取登录界面中的账号输入框对象
        this.flag=flag;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(flag==1){
            Server server=new Server();
            server.IP=ip.getText();
            server.start();
        }
        else{
            new Client();
        }
//        login.dispose();
    }
}
