package org.xi.multidatasourse.mapper2;

import org.apache.ibatis.annotations.Mapper;
import org.xi.multidatasourse.model.User2;

import java.util.List;

@Mapper
public interface User2Mapper {

    List<User2> select();
}
