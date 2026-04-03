package sn.khadim.l2gl.app.model;

@FunctionalInterface
public interface TraitementVehicule<R> {
    R executer(Vehicule vehicule);
}
