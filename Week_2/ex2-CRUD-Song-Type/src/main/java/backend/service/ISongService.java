package backend.service;

import backend.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISongService {
    Page<Song> findAll(Pageable pageable);
    Optional<Song> findById(long id);
    void delete(Song song);
    void save(Song song);
    Page<Song> findByName(Pageable pageable, String name);
    Page<Song> findByCategory(Pageable pageable, String category);
}
