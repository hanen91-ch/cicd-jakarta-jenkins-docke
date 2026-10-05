package a14.tp_finale;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EtudiantService.ajouter(new Etudiant("Tremblay", "Alice", "Informatique"));
        EtudiantService.ajouter(new Etudiant("Gagnon", "Marc", "Reseaux"));
        EtudiantService.ajouter(new Etudiant("Roy", "Sophie", "Cybersecurite"));
        EtudiantService.ajouter(new Etudiant("Bouchard", "Karim", "Genie logiciel"));
        EtudiantService.ajouter(new Etudiant("Lavoie", "Nadia", "Intelligence artificielle"));

        System.out.println("=== Liste des etudiants (demarrage) ===");
        EtudiantService.lister().forEach(System.out::println);
    }
}