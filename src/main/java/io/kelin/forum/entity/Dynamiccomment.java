package io.kelin.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
//@ApiModel(value="Dynamiccomment对象", description="")
public class Dynamiccomment implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "commentId", type = IdType.AUTO)
    private Integer commentId;

    @TableField("dynamicId")
    private Integer dynamicId;

    @TableField("userId")
    private String userId;

    private String content;

    @TableField("parentCommentId")
    private Integer parentCommentId;

    @TableField("starCount")
    private Integer starCount;

    @TableField("isDelete")
    private Integer isDelete;


}
