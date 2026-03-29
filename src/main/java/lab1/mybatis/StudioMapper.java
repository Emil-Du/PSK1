package lab1.mybatis;

import lab1.entities.Studio;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface StudioMapper {
    List<Studio> findAll();

    void insert(Studio studio);
}