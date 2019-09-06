package com.sgcc.dtomodel.wechat;

import com.sgcc.dtomodel.wechat.basedto.WeChatBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSAPITicketDTO extends WeChatBaseDTO {
    private static final long serialVersionUID = 7031276555979522587L;
    private String ticket;
    private String expires_in;
}
