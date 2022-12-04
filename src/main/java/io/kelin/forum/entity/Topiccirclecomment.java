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
//@ApiModel(value="Topiccirclecomment对象", description="")
public class Topiccirclecomment implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("circieId")
    private Integer circieId;

    @TableField("reviewerId")
    private String reviewerId;

    @TableField("parentCommentId")
    private Integer parentCommentId;

    private String content;

    @TableField("reviewTime")
    private Date reviewTime;

    @TableField("deleteTime")
    private Date deleteTime;

    @TableField("isDeleted")
    private Integer isDeleted;


}
