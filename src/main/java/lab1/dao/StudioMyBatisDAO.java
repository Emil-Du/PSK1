package lab1.dao;

import lab1.entities.Studio;
import lab1.mybatis.StudioMapper;
import org.apache.ibatis.session.SqlSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StudioMyBatisDAO {

    @Inject
    private SqlSession sqlSession;

    public List<Studio> loadAll() {
        return sqlSession.getMapper(StudioMapper.class).findAll();
    }

    public void insert(Studio studio) {
        sqlSession.getMapper(StudioMapper.class).insert(studio);
    }
}