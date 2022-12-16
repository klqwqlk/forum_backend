package io.kelin.forum.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**

 * @author kelin
 * @since 2022-12-14
 */
@Data
public class DynamicCommentVo {

    private static final long serialVersionUID=1L;

    private Integer commentId;

    private String dynamicId;

    private String fromUserId;

    private String toUserId;

    private String fromUserName;

    private String toUserName;

    private String content;

    private Integer parentCommentId;

    private Integer starCount;

    private Date commentTime;

    private Integer isDelete;

    private List<DynamicCommentVo> childComments;

}
