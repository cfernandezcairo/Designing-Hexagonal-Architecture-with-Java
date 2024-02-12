package dev.davivieira.topologyinventory.bootstrap.samples.Service;


import dev.davivieira.topologyinventory.bootstrap.samples.dto.SampleObject;
import dev.davivieira.topologyinventory.bootstrap.samples.model.SampleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PersistenceExample {

    @Autowired
    EntityManager em;

    @Transactional
    public String createEntity(SampleObject sampleObject) {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setField(sampleObject.field);
        sampleEntity.setValue(sampleObject.value);
        em.persist(sampleEntity);
        return "Entity with field "+sampleObject.field+" created!";
    }

    @Transactional
    public List<SampleEntity> getAllEntities(){
        return em.createNamedQuery("SampleEntity.findAll", SampleEntity.class)
                .getResultList();
    }
}
