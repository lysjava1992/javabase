package com.learn.spring.base.chapter25.dao;
import com.learn.spring.base.chapter25.entity.Actor;
import com.learn.spring.base.chapter25.mapper.ActorMappingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class ActorDao {
    private ActorMappingQuery actorMappingQuery;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.actorMappingQuery=new ActorMappingQuery(dataSource);
    }
    public Actor getActor(Long id) {
        return actorMappingQuery.findObject(id);
    }
    public List<Actor> getActors() {
        return actorMappingQuery.execute();
    }
    public List<Actor> getActorsByFirstName(String firstName) {

        return actorMappingQuery.execute(firstName,"firs_name");
    }

}
