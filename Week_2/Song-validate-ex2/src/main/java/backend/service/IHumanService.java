package backend.service;

import backend.model.Human;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IHumanService {
    Page<Human> findAll(Pageable pageable);
    Page<Human> findByName(Pageable pageable, String name);
    Optional<Human> findById(long id);
    void delete(long id);
    void save(Human human);
}
