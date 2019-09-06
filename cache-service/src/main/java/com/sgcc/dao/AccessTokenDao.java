package com.sgcc.dao;

import com.sgcc.dao.basedao.RedisBaseDao;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("accessToken")
public class AccessTokenDao {
    @TimeToLive
    final long livetime = 7100L; //TTL
    private String access_token;
    private String expires_in;


    public AccessTokenDao(AccessTokenDTO accessTokenDTO){
        this.access_token = accessTokenDTO.getAccess_token();
        this.expires_in = accessTokenDTO.getExpires_in();
    }

    /**
     * Dao转DTO
     * @return
     */
    public AccessTokenDTO build() {
        return new AccessTokenDTO(this.access_token,this.expires_in);
    }
}
