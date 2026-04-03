package sn.khadim.l2gl.app.model;

@FunctionalInterface
public interface TraitementChauffeurVehicule<R> {
    R executer(Chauffeur chauffeur, Vehicule vehicule);
}
