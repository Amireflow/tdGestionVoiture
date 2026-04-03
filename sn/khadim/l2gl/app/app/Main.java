package sn.Khadim.l2gl.app.app;

import sn.Khadim.l2gl.app.model.*;
import sn.Khadim.l2gl.app.service.ParcAutoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Donnees de test
        Vehicule v1 = new Vehicule("AA-123-BB", "Renault", "Clio", 2018, 45000, true, false);
        Vehicule v2 = new Vehicule("CC-456-DD", "Peugeot", "308", 2015, 120000, false, true);
        Vehicule v3 = new Vehicule("EE-789-FF", "Toyota", "Yaris", 2022, 15000, true, false);
        Vehicule v4 = new Vehicule("GG-101-HH", "BMW", "Serie3", 2010, 200000, true, false);

        Conducteur c1 = new Conducteur("Moussa", "B12345");
        Conducteur c2 = new Conducteur("Fatou", "A98765");

        Entretien e1 = new Entretien("Vidange", 15000, v1);
        Entretien e2 = new Entretien("Freins", 35000, v2);

        Location loc1 = new Location(v1, c1, LocalDate.of(2026, 1, 10), null);

        List<Vehicule> flotte = new ArrayList<>(Arrays.asList(v1, v2, v3, v4));
        ParcAutoService service = new ParcAutoService();

        // --- A. Tests ---
        // 1. Vehicule disponible ?
        TestVehicule<Vehicule> estDisponible = v -> v.isDisponible() && !v.isEnPanne();
        System.out.println("=== 1. Vehicules disponibles ===");
        service.filtrerVehicules(flotte, estDisponible).forEach(v -> System.out.println(v.getImmatriculation()));

        // 2. Vehicule en panne ?
        TestVehicule<Vehicule> estEnPanne = v -> v.isEnPanne();
        System.out.println("\n=== 2. Vehicules en panne ===");
        service.filtrerVehicules(flotte, estEnPanne).forEach(v -> System.out.println(v.getImmatriculation()));

        // 3. Kilometrage > 100000 ?
        TestVehicule<Vehicule> kmSupSeuil = v -> v.getKilometrage() > 100000;
        System.out.println("\n=== 3. Km > 100000 ===");
        service.filtrerVehicules(flotte, kmSupSeuil).forEach(v -> System.out.println(v.getImmatriculation() + " : " + v.getKilometrage() + " km"));

        // 4. Vehicule a reviser ?
        TestVehicule<Vehicule> aReviser = v -> v.getKilometrage() > 100000 || v.getAnnee() < 2015;
        System.out.println("\n=== 4. A reviser ===");
        service.filtrerVehicules(flotte, aReviser).forEach(v -> System.out.println(v.getImmatriculation()));

        // 5. Conducteur autorise ?
        TestVehicule<Conducteur> conducteurAutorise = c -> c.getPermis().startsWith("B");
        System.out.println("\n=== 5. Conducteurs autorises ===");
        System.out.println(c1.getNom() + " autorise ? " + conducteurAutorise.tester(c1));
        System.out.println(c2.getNom() + " autorise ? " + conducteurAutorise.tester(c2));

        // --- B. Transformations ---
        // 6. Resume vehicule
        TransformationVehicule<Vehicule, String> resume = v -> v.getMarque() + " " + v.getModele() + " (" + v.getAnnee() + ") - " + v.getKilometrage() + " km";
        System.out.println("\n=== 6. Resumes ===");
        service.mapperVehicules(flotte, resume).forEach(System.out::println);

        // 7. Extraire immatriculation
        TransformationVehicule<Vehicule, String> extraireImmat = v -> v.getImmatriculation();
        System.out.println("\n=== 7. Immatriculations ===");
        service.mapperVehicules(flotte, extraireImmat).forEach(System.out::println);

        // 8. Calculer age
        TransformationVehicule<Vehicule, Integer> calculerAge = v -> 2026 - v.getAnnee();
        System.out.println("\n=== 8. Ages ===");
        for (Vehicule v : flotte) {
            System.out.println(v.getImmatriculation() + " a " + calculerAge.transformer(v) + " ans");
        }

        // 9. Cout total entretien
        TransformationVehicule<Entretien, Integer> coutTotal = e -> e.getCout() + 5000;
        System.out.println("\n=== 9. Couts entretiens ===");
        System.out.println(e1.getType() + " coute " + coutTotal.transformer(e1) + " FCFA");
        System.out.println(e2.getType() + " coute " + coutTotal.transformer(e2) + " FCFA");

        // --- C. Actions ---
        // 10. Marquer en revision
        ActionVehicule<Vehicule> marquerRevision = v -> v.setEnRevision(true);
        service.appliquerSurVehicules(flotte, marquerRevision);
        System.out.println("\n=== 10. Marques en revision ===");
        for (Vehicule v : flotte) { System.out.println(v.getImmatriculation() + " en revision : " + v.isEnRevision()); }

        // 11. Augmenter km de 1000
        ActionVehicule<Vehicule> augmenterKm = v -> v.setKilometrage(v.getKilometrage() + 1000);
        service.appliquerSurVehicules(flotte, augmenterKm);
        System.out.println("\n=== 11. Km augmentes ===");
        for (Vehicule v : flotte) { System.out.println(v.getImmatriculation() + " -> " + v.getKilometrage() + " km"); }

        // 12. Terminer location
        ActionVehicule<Location> terminerLocation = loc -> loc.setDateFin(LocalDate.now());
        terminerLocation.executer(loc1);
        System.out.println("\n=== 12. Location terminee ===");
        System.out.println("Location terminee le " + loc1.getDateFin());

        // --- D. Comparaisons ---
        // 13. Tri par km croissant
        ComparaisonVehicule<Vehicule> parKm = (a, b) -> Integer.compare(a.getKilometrage(), b.getKilometrage());
        service.trierVehicules(flotte, parKm);
        System.out.println("\n=== 13. Tri par km ===");
        for (Vehicule v : flotte) { System.out.println(v.getImmatriculation() + " : " + v.getKilometrage() + " km"); }

        // 14. Tri par immatriculation alphabetique
        ComparaisonVehicule<Vehicule> parImmat = (a, b) -> a.getImmatriculation().compareTo(b.getImmatriculation());
        service.trierVehicules(flotte, parImmat);
        System.out.println("\n=== 14. Tri par plaque ===");
        for (Vehicule v : flotte) { System.out.println(v.getImmatriculation()); }
    }
}
