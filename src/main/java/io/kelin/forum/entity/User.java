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
//@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private String userId;

    @TableField("userName")
    private String userName;

    private String password;

    private String description;

    private String avatar;

    @TableField("createTime")
    private Date createTime;

    @TableField("lastLoginTime")
    private Date lastLoginTime;

    @TableField("isLimited")
    private Integer isLimited;


}
