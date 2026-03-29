package lab1.usecases;

import lab1.dao.StudioDAO;
import lab1.entities.Studio;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@Model
public class Studios {

    @Inject
    private StudioDAO studioDAO;

    private Studio studioToCreate = new Studio();
    private List<Studio> allStudios;

    @PostConstruct
    public void init() {
        loadAllStudios();
    }

    @Transactional
    public String createStudio() {
        this.studioDAO.persist(studioToCreate);
        // Svarbu: po persist grąžiname redirect, kad JSF per naujo užkrautų init()
        return "index?faces-redirect=true";
    }

    private void loadAllStudios() {
        this.allStudios = studioDAO.loadAll();
    }

    public List<Studio> getAllStudios() { return allStudios; }
    public Studio getStudioToCreate() { return studioToCreate; }
    public void setStudioToCreate(Studio studioToCreate) { this.studioToCreate = studioToCreate; }
}