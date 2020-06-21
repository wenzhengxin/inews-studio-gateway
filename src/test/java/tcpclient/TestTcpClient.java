package tcpclient;

import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.tcpclient.TcpClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tcp client test
 */
public class TestTcpClient {
    Logger logger = LoggerFactory.getLogger(TestTcpClient.class);
    @Test
    public void testTcpClient() {
        String ncsHost = "localhost";
        Integer ncsPort = 10541;
        TcpClient tcpClient = new TcpClient(ncsHost, ncsPort, Constant.TCP_CLIENT_NAME);
        Thread thread = new Thread(tcpClient);
        thread.start();

//        try {
//            Thread.sleep(2000);
//            channel = tcpClient.getChannel();
//        } catch (Exception e) {
//            logger.error(e.toString());
//        }
//        if (channel.isActive()) {
//            channel.writeAndFlush(CommandSample.RO_CREATE_XML);
//        }
    }
}
