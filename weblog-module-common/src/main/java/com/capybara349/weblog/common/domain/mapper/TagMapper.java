package com.capybara349.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.capybara349.weblog.common.domain.dos.CategoryDO;
import com.capybara349.weblog.common.domain.dos.TagDO;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 13:39
 */
public interface TagMapper extends BaseMapper<TagDO> {
    default Page<TagDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        Page<TagDO> page = new Page<>(current, size);
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, TagDO::getName, name)
                .ge(startDate != null, TagDO::getCreateTime, startDate)
                .le(endDate != null, TagDO::getCreateTime, endDate)
                .orderByDesc(TagDO::getCreateTime);
        return selectPage(page, wrapper);
    }

    /**
     * 根据标签模糊查询
     * @param key
     * @return
     */
    default List<TagDO> selectByKey(String key) {
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();

        // 构造模糊查询的条件
        wrapper.like(TagDO::getName, key).orderByDesc(TagDO::getCreateTime);

        return selectList(wrapper);
    }

    /**
     * 根据标签 ID 批量查询
     * @param tagIds
     * @return
     */
    default List<TagDO> selectByIds(List<Long> tagIds) {
        return selectList(Wrappers.<TagDO>lambdaQuery()
                .in(TagDO::getId, tagIds));
    }
}
