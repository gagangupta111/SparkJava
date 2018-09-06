import com.dto.Enum1;
import com.dto.EnumMessage;
import com.dto.EnumProto;

import java.io.IOException;

public class EnumJsonTest {

    public static void main(String[] args) throws IOException {

        System.out.println(" Hello! ");
        EnumProto.Builder builder = EnumProto.newBuilder();
        builder.setUsername("username");
        builder.setPassword("password");
        builder.setId("Id");
        builder.setField(EnumMessage.Enum1.FIELD2);

        String json = com.googlecode.protobuf.format.JsonFormat.printToString(builder.build());
        System.out.println(json);

    }

}

class JsonFormatClass {

    public static final com.google.protobuf.util.JsonFormat.Printer printer =
            com.google.protobuf.util.JsonFormat.printer()
                    .includingDefaultValueFields();
    public static final com.google.protobuf.util.JsonFormat.Parser parser =
            com.google.protobuf.util.JsonFormat.parser()
                    .ignoringUnknownFields();

}
