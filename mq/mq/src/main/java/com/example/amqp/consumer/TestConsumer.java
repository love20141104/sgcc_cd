package com.example.amqp.consumer;

import com.example.entity.TestEntity;
import com.example.entity.query.TestCacheQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    //private static  final Logger logger = LoggerFactory.getLogger("Test");

    @Autowired
    private TestCacheQueryEntity testCacheQueryEntity;
    @Autowired
    private TestEntity testEntity;

    /**
     * 测试用户提交,先存入Redis，然后发送id到mq，消费者根据id从Redis中取出对象并持久化到mysql
     */
    @JmsListener(destination = "test_mq")
    public void saveUser(String id){
        try{
            testEntity.saveUesr(
                    testCacheQueryEntity
                            .findTestRedisDaoById(id)
                            .build()
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
