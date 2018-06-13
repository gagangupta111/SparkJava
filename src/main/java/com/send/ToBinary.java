package com.send;

import com.dto.RequestDTO;

import java.io.File;
import java.io.FileOutputStream;
public class ToBinary {

    public static void main(String[] args) throws Exception{

        FileOutputStream fop = null;
        File file;

            file = new File("username.txt");
            fop = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            RequestDTO.Builder requestDTOBuilder = RequestDTO.newBuilder();
            requestDTOBuilder.setUsername("username");
            requestDTOBuilder.setPassword("password");
            RequestDTO requestDTO = requestDTOBuilder.build();
            requestDTO.writeTo(fop);

            fop.flush();
            fop.close();

/*


        PrintWriter writer = new PrintWriter("username.txt", "UTF-8");
        writer.println(requestDTO.writeTo(writer));
        writer.close();

        writer = new PrintWriter("password.txt", "UTF-8");
        writer.println("password".getBytes());
        writer.close();

*/

    }

}
