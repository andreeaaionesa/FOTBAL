package repository;

import model.FotbalPlayer;

import java.util.List;

public interface FotbalPlayerRepo extends Repository<Integer, FotbalPlayer>{
    List<FotbalPlayer> findByName(String name);
    List<FotbalPlayer> findAllfotbalplayers();
}
