package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CateGoryMapper {
    //添加文章
    @Insert("insert into category(category_name,category_alias,create_time,update_time)" +
            "values(#{categoryName},#{categoryAlias},#{createTime},#{updateTime})")
    void add(Category category, Integer userId);


    //查询列表
    @Select("select * from category where create_user =#{userId}")
    List<Category> list(Integer userId);

    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    //更新分类
    @Update("update category set category_time=#{categoryTime},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
}
