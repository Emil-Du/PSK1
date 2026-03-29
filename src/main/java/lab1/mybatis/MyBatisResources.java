package lab1.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
public class MyBatisResources {
    @Produces
    @ApplicationScoped
    public SqlSessionFactory createSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Produces
    @jakarta.enterprise.context.RequestScoped
    public org.apache.ibatis.session.SqlSession createSqlSession(SqlSessionFactory factory) {
        return factory.openSession();
    }
}