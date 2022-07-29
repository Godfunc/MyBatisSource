package com.godfunc;

import com.godfunc.entity.SingleTable;
import com.godfunc.mapper.SingleTableMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        config.setCacheEnabled(true);
        config.setLogImpl(StdOutImpl.class);
        // config.addMappers("com.godfunc.mapper"); // 会为这个
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(Resources.getResourceAsStream("mapper/SingleTableMapper.xml"),
                config, Resources.getResourceURL("mapper/SingleTableMapper.xml").getFile(),
                config.getSqlFragments());
        xmlMapperBuilder.parse();
        config.setEnvironment(development);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SingleTableMapper mapper = sqlSession.getMapper(SingleTableMapper.class);

/*        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<SingleTable> list1 = mapper.list();
            System.out.println(list1);
        }, "t2").start();*/
        List<SingleTable> list = mapper.list(10, "single_table");
        System.out.println(list.hashCode());
        list.add(new SingleTable());
        System.out.println(list.hashCode());
        List<SingleTable> list2 = mapper.list(10, "single_table");
        // sqlSession.commit();


        /*SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SingleTableMapper sqlSession1Mapper = sqlSession1.getMapper(SingleTableMapper.class);
        List<SingleTable> list1 = sqlSession1Mapper.list(11, "single_table");
*/

        System.out.println(list);
        sqlSession.close();
    }
}
