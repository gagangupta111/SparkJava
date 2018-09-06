import com.dto.Enum1;
import com.dto.EnumProto;
import com.google.protobuf.util.JsonFormat;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class EnumJsonTest {

    public static void main(String[] args) throws IOException {

        System.out.println(" Hello! ");
        EnumProto.Builder builder = EnumProto.newBuilder();
        builder.setUsername("User1");
        builder.setPassword("password");
        builder.setId("Id");

        EnumProto enumProto = builder.build();
        ObjectMapper objectMapper = new ObjectMapper();

        String json  = objectMapper.writeValueAsString(enumProto);
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
