package com.sgcc.dao;

import com.example.constant.RedisConstants;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("jsApiTicketDao")
public class JSApiTicketDao {
    @TimeToLive
    long livetime = 7200L; //TTL
    @Id
    private String id = RedisConstants.JSAPI_TICKET_ID;
    private String ticket;
    private String expires_in;

    public JSApiTicketDao(JSAPITicketDTO jsapiTicketDTO){
        this.ticket = jsapiTicketDTO.getTicket();
        this.expires_in = jsapiTicketDTO.getExpires_in();
    }

    /**
     * Dao转DTO
     * @return
     */
    public JSAPITicketDTO build() {
        return new JSAPITicketDTO(this.ticket,this.expires_in);
    }
}
