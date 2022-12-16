package io.kelin.forum.vo;



import java.util.Date;
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
public class UserVo implements Serializable {

    private static final long serialVersionUID=1L;



    private String userId;

    private String userName;

    private String description;

    private String avatar;

    private Date createTime;

    private Date lastLoginTime;

    private Integer isLimited;

    private String token;
}
