package e.commerce.haowu;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import e.commerce.haowu.frame.utils.MD5Util;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.mapper.*;
import e.commerce.haowu.system.service.SeckillGoodsInfoService;
import e.commerce.haowu.system.service.UserInfoService;
import e.commerce.haowu.system.vo.OrderVo;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

@SpringBootTest
class HaowuApplicationTests {

    @Test
    void contextLoads() {
        String zz15083511006 = MD5Util.md5("zz15083511006");
        System.out.println(zz15083511006);
    }

}
