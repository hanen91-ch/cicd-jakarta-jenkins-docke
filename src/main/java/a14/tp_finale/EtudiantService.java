package a14.tp_finale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtudiantService {
    private static final List<Etudiant> ETUDIANTS = new ArrayList<>();

    public static synchronized void ajouter(Etudiant e) {
        ETUDIANTS.add(e);
    }

    public static synchronized List<Etudiant> lister() {
        return Collections.unmodifiableList(new ArrayList<>(ETUDIANTS));
    }
}
