package com.sgcc.service;

import com.sgcc.FastDFSClient.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FastDFSService {
    @Autowired
    private FastDFSClient fastDFSClient;
    public String uploadIMG( byte[] bytes ,String suffix ){
        return fastDFSClient.uploadFile(bytes,suffix);
    }

    public String uploadIMG( MultipartFile srcFile ){
        try {
            return fastDFSClient.uploadFile(srcFile);
        }
        catch (IOException e )
        {
            return "";
        }
    }
}
