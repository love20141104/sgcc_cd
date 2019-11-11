package com.sgcc.dtomodel.wechat;

import com.sgcc.dtomodel.wechat.basedto.WeChatBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryMaterialsViewDTO extends WeChatBaseDTO implements Serializable {

    private static final long serialVersionUID = -4735098750523869552L;

    private String type;

    private String media_id;

    private String created_at;

}
