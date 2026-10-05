package a14.tp_finale;

public record Etudiant(String nom, String prenom, String specialite) {
    @Override
    public String toString() {
        return prenom + " " + nom + " - " + specialite;
    }
}
