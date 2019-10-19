package com.sgcc.service;

import com.sgcc.FastDFSClient.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastDFSService {
    @Autowired
    private FastDFSClient fastDFSClient;
    public String uploadIMG( byte[] bytes ,String suffix ){
        return fastDFSClient.uploadFile(bytes,suffix);
    }
}
