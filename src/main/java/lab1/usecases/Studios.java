package lab1.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lab1.dao.StudioDAO;
import lab1.entities.Studio;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
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
                return "index?faces-redirect=true";
    }

    private void loadAllStudios() {
        this.allStudios = studioDAO.loadAll();
    }

    public List<Studio> getAllStudios() { return allStudios; }
    public Studio getStudioToCreate() { return studioToCreate; }
    public void setStudioToCreate(Studio studioToCreate) { this.studioToCreate = studioToCreate; }
}