package org.xi.multidatasourse.controller;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/exec-sql")
    public List execSql(@RequestBody String sql) {
        List<Object> result = new LinkedList<>();
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        for (SQLStatement statement : sqlStatements) {
            if (statement instanceof SQLSelectStatement) {
                result.add(jdbcTemplate.queryForList(statement.toString()));
            } else {
//                jdbcTemplate.execute(statement.toString());
                result.add(jdbcTemplate.update(statement.toString()));
            }
            System.out.println(statement.getClass());
        }
        return result;
    }
}
