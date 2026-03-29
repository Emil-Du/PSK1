package lab1.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lab1.dao.StudioMyBatisDAO;
import lab1.entities.Studio;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class StudiosMyBatis {

    @Inject
    private StudioMyBatisDAO studioMyBatisDAO;

    private Studio studioToCreate = new Studio();
    private List<Studio> allStudios;

    @PostConstruct
    public void init() {
        try {
            this.allStudios = studioMyBatisDAO.loadAll();
        } catch (Exception e) {
            this.allStudios = new java.util.ArrayList<>();
            System.out.println("MyBatis: Lentelės dar nėra, laukiam kol JPA jas sukurs...");
        }
    }

    @Transactional
    public void createStudio() {
        studioMyBatisDAO.insert(studioToCreate);
    }

    public List<Studio> getAllStudios() { return allStudios; }
    public Studio getStudioToCreate() { return studioToCreate; }
    public void setStudioToCreate(Studio studioToCreate) { this.studioToCreate = studioToCreate; }
}