package io.kelin.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author kelin
 * @since 2022-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@ApiModel(value="Letter对象", description="")
public class Letter implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("fromId")
    private String fromId;

    @TableField("toId")
    private String toId;

    private String content;

    @TableField("sendTime")
    private Date sendTime;

    @TableField("deleteTime")
    private Date deleteTime;

    @TableField("isReceived")
    private Integer isReceived;

    @TableField("isDeleted")
    private Integer isDeleted;


}
