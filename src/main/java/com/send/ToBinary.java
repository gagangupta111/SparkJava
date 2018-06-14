package com.send;

import com.dto.RequestDTO;

import java.io.File;
import java.io.FileInputStream;
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
            requestDTOBuilder.setUsername("gagan");
            requestDTOBuilder.setPassword("gupta");
            RequestDTO requestDTO = requestDTOBuilder.build();
            requestDTO.writeTo(fop);

            fop.flush();
            fop.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        RequestDTO requestDTO1 = RequestDTO.parseFrom(fileInputStream);
        System.out.println(requestDTO1.getUsername());
        System.out.println(requestDTO1.getPassword());


    }

}
