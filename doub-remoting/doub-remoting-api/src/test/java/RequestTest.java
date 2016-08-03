import com.xxx.doub.remoting.exchange.RequestProto;
import org.junit.Test;

/**
 * Created by louyihua on 2016/8/2 15:52.
 */
public class RequestTest {

    @Test
    public void test()
    {
        RequestProto.Request request = RequestProto.Request.newBuilder().setId(123).setType("message").
    }
}
