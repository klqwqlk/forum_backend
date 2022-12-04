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
//@ApiModel(value="Dynamic对象", description="")
public class Dynamic implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "dynamicId", type = IdType.AUTO)
    private Integer dynamicId;

    @TableField("userId")
    private String userId;

    private String content;

    @TableField("starCount")
    private Integer starCount;

    @TableField("storageCount")
    private Integer storageCount;

    @TableField("createTime")
    private Date createTime;

    @TableField("editTime")
    private Date editTime;


}
