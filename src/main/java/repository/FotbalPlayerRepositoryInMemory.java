package repository;

import model.FotbalPlayer;
import java.util.List;
import java.util.stream.Collectors;

public class FotbalPlayerRepositoryInMemory extends AbstractRepository< Integer, FotbalPlayer> implements FotbalPlayerRepo {

    @Override
    public List<FotbalPlayer> findAllfotbalplayers() {
        return getAll().stream().collect(Collectors.toList());
    }
    @Override
    public List<FotbalPlayer> findByName(String name) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
