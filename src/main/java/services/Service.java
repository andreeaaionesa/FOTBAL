package services;

import model.FotbalPlayer;
import model.Team;
import repository.FotbalPlayerRepo;
import repository.TeamRepo;
import repository.RepositoryException;

import java.util.List;

public class Service{

    public FotbalPlayerRepo ar;

    private TeamRepo br;

    public Service(FotbalPlayerRepo ar, TeamRepo br) {
        this.ar = ar;
        this.br = br;
    }

    public int addF(String name, int age, String height, String experience) throws ServicesException{
        try {
            FotbalPlayer f = new FotbalPlayer(name, age, height, experience);
            FotbalPlayer newf = ar.add(f);
            return newf.getId();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding cake"+ex);
        }
    }

    public List<FotbalPlayer> getFByName(String name){
        return ar.findByName(name);
    }

    public List<Team> getTeamByRace(String name) {
        return br.filterByF(name);
    }

    public void addTeam(String tname, String country, String game, FotbalPlayer f) throws ServicesException{
        try {
            Team en = new Team(tname, country, game ,f);
            ar.update(f.getId(), f);
            br.add(en);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding registration"+er);
        }
    }
}

