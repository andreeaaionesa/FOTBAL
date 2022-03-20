package repository;

import model.Team;

import java.util.List;

public interface TeamRepo extends Repository<Integer, Team> {
    List<Team> filterByF(String name);
}
