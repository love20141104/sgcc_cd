package com.sgcc.dtomodel.wechat;

import com.sgcc.dtomodel.wechat.basedto.WeChatBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempMessageDTO extends WeChatBaseDTO {

    private static final long serialVersionUID = -5584829829957012993L;

    private String msgid;



}
