package com.sgcc.dao;

import com.example.constant.RedisConstants;
import com.sgcc.dao.basedao.RedisBaseDao;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
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
@RedisHash("accessToken")
public class AccessTokenDao {
    @TimeToLive
    long livetime = 7200L; //TTL
    @Id
    private String id = RedisConstants.ACCESS_TOKEN_ID;
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
