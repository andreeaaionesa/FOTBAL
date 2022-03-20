package repository;

import model.Team;

import java.util.List;
import java.util.stream.Collectors;


public class TeamRepositoryInMemory extends AbstractRepository<Integer, Team> implements TeamRepo {
    @Override
    public List<Team> filterByF(String name) {
        return getAll().stream().filter(x->x.getF().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
