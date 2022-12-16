package io.kelin.forum.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@ApiModel(value="Dynamic对象", description="")
public class DynamicVo implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer dynamicId;

    private String userId;

    private String userName;

    private String content;

    private Integer starCount;

    private Integer storageCount;

    private Date createTime;

    private Date editTime;

    private Boolean starFlag;

    private Boolean storageFlag;

}