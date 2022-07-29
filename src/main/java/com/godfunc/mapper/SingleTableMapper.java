package com.godfunc.mapper;

import com.godfunc.entity.SingleTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingleTableMapper {
    List<SingleTable> list(@Param("limit") int limit, @Param("table") String table);
}
