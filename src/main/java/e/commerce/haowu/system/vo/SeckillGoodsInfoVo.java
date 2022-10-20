package e.commerce.haowu.system.vo;

import e.commerce.haowu.system.entity.SeckillGoodsInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SeckillGoodsInfoVo extends SeckillGoodsInfo {

    private String goodsName;

    private Double goodsPrice;

    private String goodsImg;

    private String goodsDetail;

    private int goodsStock;

}
