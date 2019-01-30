import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
    private static final long serialVersionUID = 1L;

    public Client() {
        super();
        this.setSize(800, 640);
        Screen p = new Screen();
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(p, SwingConstants.CENTER);
        new Thread(p).start();
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                setVisible(true);
            }});
    }

//    public static void main(String[] args) {
//        new Client();
//    }
}

class Screen extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    private Image cimage;

    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5001);// 监听5001端口获取图片
            while (true) {
                Socket s = null;
                try {
                    s = ss.accept();
                    ZipInputStream zis = new ZipInputStream(s.getInputStream());
                    zis.getNextEntry();
                    cimage = ImageIO.read(zis);// 转换为图片
                    repaint();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (s != null) {
                        try {
                            s.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        catch (Exception e) {
        }
        finally {
            if (ss != null) {
                try {
                    ss.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Screen() {
        super();
        this.setLayout(null);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(cimage, 0, 0, null);
    }
}
