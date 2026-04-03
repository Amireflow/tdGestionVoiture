package sn.khadim.l2gl.app.app;

import sn.khadim.l2gl.app.model.Chauffeur;
import sn.khadim.l2gl.app.model.DateUtils;
import sn.khadim.l2gl.app.model.Etat;
import sn.khadim.l2gl.app.model.Permis;
import sn.khadim.l2gl.app.model.TraitementChauffeur;
import sn.khadim.l2gl.app.model.TraitementChauffeurVehicule;
import sn.khadim.l2gl.app.model.TraitementVehicule;
import sn.khadim.l2gl.app.model.Type;
import sn.khadim.l2gl.app.model.Vehicule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final int AGE_RETRAITE = 60;

    public static void main(String[] args) {
        // Creation des vehicules
        Vehicule camion = new Vehicule(
                "DK-4567-AA",
                "Mercedes",
                Type.LOURD,
                Etat.DISPO,
                DateUtils.createCalendar(2016, 3, 15)
        );
        Vehicule taxi = new Vehicule(
                "DK-8910-BB",
                "Toyota",
                Type.LEGER,
                Etat.DISPO,
                DateUtils.createCalendar(2022, 7, 10)
        );
        Vehicule fourgon = new Vehicule(
                "DK-1112-CC",
                "Iveco",
                Type.LOURD,
                Etat.INDISPO,
                DateUtils.createCalendar(2014, 11, 5)
        );

        // Creation des chauffeurs
        Chauffeur ali = new Chauffeur(
                1,
                "Ali",
                "Ndiaye",
                DateUtils.createCalendar(1988, 6, 21),
                Permis.B,
                Etat.DISPO
        );
        Chauffeur fatou = new Chauffeur(
                2,
                "Fatou",
                "Diop",
                DateUtils.createCalendar(1962, 2, 4),
                Permis.A,
                Etat.DISPO
        );
        Chauffeur ibra = new Chauffeur(
                3,
                "Ibrahima",
                "Fall",
                DateUtils.createCalendar(1995, 9, 13),
                Permis.B,
                Etat.INDISPO
        );

        List<Vehicule> vehicules = new ArrayList<>(List.of(camion, taxi, fourgon));
        List<Chauffeur> chauffeurs = List.of(ali, fatou, ibra);

        // 1. Verifier si un chauffeur peut conduire un vehicule
        TraitementChauffeurVehicule<Boolean> peutConduire = (chauffeur, vehicule) -> {
            return chauffeur.getPermis().peutConduire(vehicule.getType());
        };

        // 2. Verifier si un chauffeur et un vehicule sont disponibles
        TraitementChauffeurVehicule<Boolean> estDisponible = (chauffeur, vehicule) -> {
            return chauffeur.estDisponible() && vehicule.estDisponible();
        };

        // 3. Verifier si un vehicule est amorti
        TraitementVehicule<Boolean> estAmorti = vehicule -> {
            return vehicule.estAmorti();
        };

        // 4. Verifier si un chauffeur doit partir a la retraite
        TraitementChauffeur<Boolean> partALaRetraite = chauffeur -> {
            return chauffeur.doitPartirALaRetraite(AGE_RETRAITE);
        };

        // 5. Transformer un chauffeur en String
        TraitementChauffeur<String> transformerEnTexte = chauffeur -> {
            return chauffeur.toString();
        };

        // 6. Comparer deux chauffeurs selon l'age
        Comparator<Chauffeur> comparerParAge = (chauffeur1, chauffeur2) -> {
            return Integer.compare(chauffeur1.getAge(), chauffeur2.getAge());
        };

        // 7. Comparer deux vehicules selon le type
        Comparator<Vehicule> comparerParType = (vehicule1, vehicule2) -> {
            return vehicule1.getType().compareTo(vehicule2.getType());
        };

        System.out.println("1. Verifier si un chauffeur peut conduire un vehicule");
        System.out.println(ali.getNomComplet() + " peut conduire le camion ? " + peutConduire.executer(ali, camion));
        System.out.println(fatou.getNomComplet() + " peut conduire le camion ? " + peutConduire.executer(fatou, camion));
        System.out.println(fatou.getNomComplet() + " peut conduire le taxi ? " + peutConduire.executer(fatou, taxi));

        System.out.println("\n2. Verifier si un chauffeur et son vehicule sont disponibles");
        System.out.println(ali.getNomComplet() + " + " + camion.getImmatriculation() + " : "
                + estDisponible.executer(ali, camion));
        System.out.println(ibra.getNomComplet() + " + " + taxi.getImmatriculation() + " : "
                + estDisponible.executer(ibra, taxi));
        System.out.println(ali.getNomComplet() + " + " + fourgon.getImmatriculation() + " : "
                + estDisponible.executer(ali, fourgon));

        System.out.println("\n3. Verifier si un vehicule est amorti (5 ans ou plus)");
        for (Vehicule vehicule : vehicules) {
            if (estAmorti.executer(vehicule)) {
                System.out.println(vehicule.getImmatriculation() + " est amorti ? true (" + vehicule.getAge() + " ans)");
            } else {
                System.out.println(vehicule.getImmatriculation() + " est amorti ? false (" + vehicule.getAge() + " ans)");
            }
        }

        System.out.println("\n4. Verifier si un chauffeur doit partir a la retraite");
        for (Chauffeur chauffeur : chauffeurs) {
            System.out.println(
                    chauffeur.getNomComplet() + " doit partir ? " + partALaRetraite.executer(chauffeur)
                            + " (" + chauffeur.getAge() + " ans)"
            );
        }

        System.out.println("\n5. Transformer un chauffeur en String");
        for (Chauffeur chauffeur : chauffeurs) {
            String texte = transformerEnTexte.executer(chauffeur);
            System.out.println(texte);
        }

        System.out.println("\n6. Comparer deux chauffeurs selon l'age");
        List<Chauffeur> chauffeursTries = new ArrayList<>(chauffeurs);
        chauffeursTries.sort(comparerParAge);
        for (Chauffeur chauffeur : chauffeursTries) {
            System.out.println(chauffeur.getNomComplet() + " - " + chauffeur.getAge() + " ans");
        }

        System.out.println("\n7. Comparer deux vehicules selon le type");
        List<Vehicule> vehiculesTries = new ArrayList<>(vehicules);
        vehiculesTries.sort(comparerParType);
        for (Vehicule vehicule : vehiculesTries) {
            System.out.println(vehicule);
        }
    }
}
