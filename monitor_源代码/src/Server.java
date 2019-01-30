import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;

public class Server extends Thread {
    private Dimension screenSize;
    private Rectangle rectangle;
    private Robot robot;
    public String IP="";
    public Server() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println(screenSize);       //检查时的改动
//        screenSize.width=1000;
//        screenSize.height=500;
        rectangle = new Rectangle(screenSize);  //监视屏幕的区域范围
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.print("running");
    }

    public void run() {
        ZipOutputStream os = null;
        Socket socket = null;
        while (true) {
            try {
                socket = new Socket(IP, 5001);
                BufferedImage image = robot.createScreenCapture(rectangle);//自动截图
                os = new ZipOutputStream(socket.getOutputStream());//压缩流

                os.setLevel(9);
                os.putNextEntry(new ZipEntry("test.jpg"));
                ImageIO.write(image,"jpg",os);
                os.close();
                Thread.sleep(20);// 50帧截图
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ioe) {
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    }
                    catch (IOException e) {
                    }
                }
            }
        }
    }
}