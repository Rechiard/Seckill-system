package e.commerce.haowu.system.vo;

import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailVo {

    private UserInfo user;

    private SeckillGoodsInfoVo seckillGoodsInfoVo;

    private int seckillStatus;

    private int remainSeconds;
}
