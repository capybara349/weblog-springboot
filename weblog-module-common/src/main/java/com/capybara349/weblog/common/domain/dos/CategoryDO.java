package com.capybara349.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 13:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_category")
public class CategoryDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

    private Integer articlesTotal;
}
