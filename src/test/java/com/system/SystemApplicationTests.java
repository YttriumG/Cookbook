package com.system;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SystemApplicationTests {

    @Test
    void contextLoads() {
        String str = "{\"主料\":{\"千张\":\"一张\",\"火腿\":\"两根\"},\"辅料\":{\"植物油\":\"适量\",\"盐\":\"适量\",\"豆瓣酱\":\"一大勺\",\"花椒粉\":\"2克\",\"生抽\":\"5克\",\"大蒜\":\"2瓣\",\"姜\":\"5克\",\"大葱\":\"20克\",\"辣皮子\":\"4个\"}}";
        JSONObject jsonObject = JSONObject.fromObject(str);

    }

}
