package com.sgcc.dtomodel.wechat;

import com.sgcc.dtomodel.wechat.basedto.WeChatBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryMaterialsSubmitDTO extends WeChatBaseDTO implements Serializable {

    private static final long serialVersionUID = 1703986959739278874L;
    private String access_token;

    private String type;

    private MultipartFile media;

}
