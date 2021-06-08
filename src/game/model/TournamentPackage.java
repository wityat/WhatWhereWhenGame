package game.model;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class TournamentPackage extends Tournament{
    private final List<Tournament> packages = new ArrayList<Tournament>();
    private final List<TournamentPackage>subgroups = new ArrayList<TournamentPackage> ();

    public List<Tournament> getPackages() {
        return FXCollections.observableArrayList(packages);
    }

    public void setPackages(List<Tournament> packages) {
        this.packages.addAll(packages);
    }

    public List<TournamentPackage> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<TournamentPackage> subgroups) {
        this.subgroups.addAll(subgroups);
    }
}
