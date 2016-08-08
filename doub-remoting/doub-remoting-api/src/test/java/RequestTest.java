import com.google.protobuf.ByteString;
import com.xxx.doub.remoting.exchange.protobuf.RequestProto;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by louyihua on 2016/8/2 15:52.
 */
public class RequestTest {

    @Test
    public void testWrite() throws IOException {
        RequestProto.Request.Builder messageBuilder = RequestProto.Request.newBuilder();
        messageBuilder.setEventType(RequestProto.Request.EventType.MESSAGE);
        messageBuilder.setId(1);
        messageBuilder.setMessage(RequestProto.Request.Message.newBuilder().setData("12312321323"));
        RequestProto.Request messageRequest = messageBuilder.build();
        System.out.println(messageRequest);

        File file = new File("d:/test1.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        RequestProto.Request.Builder fileBuilder = RequestProto.Request.newBuilder();
        fileBuilder.setEventType(RequestProto.Request.EventType.MESSAGE);
        fileBuilder.setId(2);

        RequestProto.Request.File.Builder builder = RequestProto.Request.File.newBuilder();
        builder.setFileLength(file.length());
        builder.setFileType("jpg".toLowerCase());
        builder.setData(ByteString.readFrom(fileInputStream));
        fileBuilder.setFile(builder);
        RequestProto.Request fileRequest = fileBuilder.build();
        System.out.println(fileRequest);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\text1.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(fileRequest);

    }

    @Test
    public void testRead() throws IOException, ClassNotFoundException {
        File file = new File("d:\\test11.jpg");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("d:\\text1.txt")));
        RequestProto.Request request = (RequestProto.Request)objectInputStream.readObject();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(request.getFile().getData().toByteArray());
        fileOutputStream.close();

    }

//    @Test
//    public void testClient()
//    {
//        Client.class
//    }
}
