package com.sgcc.dtomodel.wechat;

import com.sgcc.dtomodel.wechat.basedto.WeChatBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO extends WeChatBaseDTO {
    private static final long serialVersionUID = 8195025239454582629L;
    private String access_token;
    private String expires_in;
}
