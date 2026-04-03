package sn.khadim.l2gl.app.model;

@FunctionalInterface
public interface TraitementChauffeur<R> {
    R executer(Chauffeur chauffeur);
}
