package com.capybara349.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.capybara349.weblog.common.domain.dos.CategoryDO;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 13:39
 */
public interface CategoryMapper extends BaseMapper<CategoryDO> {

    /**
     * 根据用户名查询
     *
     * @param categoryName
     * @return
     */
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);

        // 执行查询
        return selectOne(wrapper);
    }

    default Page<CategoryDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询第几页、每页多少数据)
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        wrapper
                .like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);//按时间倒序

        return selectPage(page, wrapper);
    }
}
