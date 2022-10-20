package e.commerce.haowu.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Timestamp;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import e.commerce.haowu.frame.dto.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("goods_info")
@ApiModel(value="GoodsInfo对象", description="")
public class GoodsInfo extends BaseEntity {


    @ApiModelProperty(value = "商品ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品名称")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty(value = "商品图片")
    @TableField("goods_img")
    private String goodsImg;

    @ApiModelProperty(value = "商品详情")
    @TableField("goods_detail")
    private String goodsDetail;

    @ApiModelProperty(value = "商品价格")
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品类型：1电视机，2录像机，3手机，4电子平板，5电脑，6游戏机，7收音机，8投影仪")
    @TableField("goods_type")
    private Integer goodsType;

    @ApiModelProperty(value = "商品库存")
    @TableField("goods_stock")
    private Integer goodsStock;

    @ApiModelProperty(value = "商品上架时间")
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiModelProperty(value = "状态，1为正常，2为删除")
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;


}
