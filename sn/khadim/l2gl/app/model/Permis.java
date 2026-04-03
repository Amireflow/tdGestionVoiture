package sn.khadim.l2gl.app.model;

public enum Permis {
    A,
    B;

    public boolean peutConduire(Type typeVehicule) {
        if (this == B) {
            return true;
        }

        return typeVehicule == Type.LEGER;
    }
}
