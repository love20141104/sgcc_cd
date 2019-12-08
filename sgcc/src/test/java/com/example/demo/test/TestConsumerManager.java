package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.sgcc.DemoApplication;
import com.sgcc.dto.ConsumerManagerDTO;
import com.sgcc.dto.ConsumerManagerInsertDTO;
import com.sgcc.service.ConsumerManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConsumerManager.class, DemoApplication.class})
public class TestConsumerManager {

    private Logger logger = Logger.getLogger(TestConsumerManager.class.toString());
    @Autowired
    private ConsumerManagerService consumerManagerService;

    /**
     * 查询所有客户经理
     */
    @Test
    public void selectConsumerManager(){
        logger.info(JSON.toJSONString(consumerManagerService.selectConsumerManager().getData()));
    }

    /**
     * 查询指定用户对应的客户经理
     */
    @Test
    public void selectConsumerManagerByUserId(){
//        logger.info(JSON.toJSONString(consumerManagerService.selectConsumerManagerByUserId("operid").getData()));
    }

    /**
     * 新增客户经理
     */
    @Test
    public void insertConsumerManager(){
        List<String> xxx = new ArrayList<String>(){
            private static final long serialVersionUID = 2357706651819257209L;

            {
            add("双流区");
            add("金牛区");
            add("武侯区");
            add("青羊区");
            add("成华区");
        }};
        for(int i = 0;i<100;i++) {
            ConsumerManagerInsertDTO consumerManagerInsertDTO = new ConsumerManagerInsertDTO(
                    "梁伟"+i,
                    "13054126578",
                    xxx.get(i%5)+"XX街道，XX街道，XX街道，XX街道，XX街道，XX街道，XX街道，XX街道，XX街道",
                    xxx.get(i%5),
                    "职责范围：xxxxxxxxxxxxxxxxxxxx",
                    "工作日9:00——18:00",
                    "86241245",
                    xxx.get(i%5)+"供电局",
                    "抄表员",
                    "url:12asd456w7ad2wa3d156a798qd5a34w8"
            );
            logger.info(JSON.toJSONString(consumerManagerService.insertConsumerManager(consumerManagerInsertDTO).getData()));
        }
    }

    /**
     * 删除客户经理
     */
    @Test
    public void deleteConsumerManager(){
        logger.info(JSON.toJSONString(consumerManagerService.deleteConsumerManager("1aeb339b-4379-46a4-bde4-042af2260787").getData()));
    }

    /**
     * 批量删除客户经理
     */
    @Test
    public void deleteConsumerManagers(){
        List<String> ids = new ArrayList<>();
        ids.add("1b85549c-bafb-4dc5-99cc-704eb7393a98");
        ids.add("1c1c6123-febb-4595-b187-14e72a4aec6c");
        logger.info(JSON.toJSONString(consumerManagerService.deleteConsumerManagers(ids).getData()));
    }

    /**
     * 修改客户经理信息
     */
    @Test
    public void updateConsumerManager(){
        ConsumerManagerDTO consumerManagerDTO = new ConsumerManagerDTO(
                "25654627-4127-494b-ba5c-8343d59d949d",
                "李四",
                "13054126578",
                "双流区XX街道，XX街道，XX街道，XX街道，XX街道，XX街道，XX街道，XX街道，XX街道",
                "双流区",
                "职责范围：xxxxxxxxxxxxxxxxxxxx",
                "工作日9:00——18:00",
                "86241245",
                "双流区供电局",
                "抄表员",
                "url:12asd456w7ad2wa3d156a798qd5a34w8"

        );

        logger.info(JSON.toJSONString(consumerManagerService.updateConsumerManager(consumerManagerDTO).getData()));
    }
}
