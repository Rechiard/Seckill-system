package e.commerce.haowu.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author bobo
 * @since 2022-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SeckillGoodsInfo对象", description="")
public class SeckillGoodsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "秒杀")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "秒杀价格")
    private Double seckillPrice;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "状态，1位正常，2为删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer status;


}
