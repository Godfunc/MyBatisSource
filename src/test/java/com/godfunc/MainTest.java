package com.godfunc;

import com.godfunc.entity.SingleTable;
import com.godfunc.mapper.SingleTableMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MainTest {

    @Test
    public void test() throws IOException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/xiaohaizi");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        Environment development = new Environment("development", new JdbcTransactionFactory(), dataSource);
        Configuration config = new Configuration();
        config.addLoadedResource("classpath*:/mapper/**/*.xml");
        // config.addMappers("com.godfunc.mapper"); // 会为这个
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(Resources.getResourceAsStream("mapper/SingleTableMapper.xml"), config, Resources.getResourceURL("mapper/SingleTableMapper.xml").getFile(), config.getSqlFragments());
        xmlMapperBuilder.parse();
        config.setEnvironment(development);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SingleTableMapper mapper = sqlSession.getMapper(SingleTableMapper.class);
        List<SingleTable> list = mapper.list();
        System.out.println(list);
        sqlSession.close();
    }
}
