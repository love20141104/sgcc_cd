package com.example.dao;

import com.example.dao.basedao.RedisBaseDao;
import com.example.test.TestRedisDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@EqualsAndHashCode(callSuper = true)
@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("testRedis")
public class TestRedisDao extends RedisBaseDao {
    private static final long serialVersionUID = 3838970203906189526L;
    @Id
    private  String id;
    private String name;
    private int age;

    public TestRedisDTO build(){
        return new TestRedisDTO(id,name,age);
    }
}
