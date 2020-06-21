package tcpserver;

import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.session.SessionUtil;
import com.trust.inews.studiogate.tcpclient.TcpClient;
import org.junit.Test;

/**
 * TcpServer test
 */
public class TestTcpServer {
    //@Test
    public void testSendRoCreate() {
        String ncsHost = "localhost";
        Integer ncsPort = 10541;
        TcpClient tcpClient = new TcpClient(ncsHost, ncsPort, Constant.TCP_CLIENT_NAME);
        Thread thread = new Thread(tcpClient);
        thread.start();
        try {
            Thread.sleep(2000);
            String xml = "";
            io.netty.channel.Channel channel = SessionUtil.getClientChannel(Constant.TCP_CLIENT_NAME);
            channel.writeAndFlush(xml);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testSendRoElementAction() {
        String ncsHost = "localhost";
        Integer ncsPort = 10541;
        TcpClient tcpClient = new TcpClient(ncsHost, ncsPort, Constant.TCP_CLIENT_NAME);
        Thread thread = new Thread(tcpClient);
        thread.start();
        try {
            Thread.sleep(2000);
            String xml = "";
            io.netty.channel.Channel channel = SessionUtil.getClientChannel(Constant.TCP_CLIENT_NAME);
            channel.writeAndFlush(xml);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
