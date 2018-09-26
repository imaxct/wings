package cn.sduonline.wings.dao.mapper;

import cn.sduonline.wings.model.Select;
import cn.sduonline.wings.model.SelectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SelectMapper {
    long countByExample(SelectExample example);

    int deleteByExample(SelectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Select record);

    int insertSelective(Select record);

    List<Select> selectByExample(SelectExample example);

    Select selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Select record, @Param("example") SelectExample example);

    int updateByExample(@Param("record") Select record, @Param("example") SelectExample example);

    int updateByPrimaryKeySelective(Select record);

    int updateByPrimaryKey(Select record);
}