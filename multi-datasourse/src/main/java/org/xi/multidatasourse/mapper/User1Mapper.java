package org.xi.multidatasourse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.xi.multidatasourse.model.User1;

import java.util.List;

@Mapper
public interface User1Mapper {

    List<User1> select();
}
