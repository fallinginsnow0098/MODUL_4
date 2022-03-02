package backend.service.imp;

import backend.model.Song;
import backend.repository.ISongRepository;
import backend.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService implements ISongService {

    @Autowired
    private ISongRepository songRepository;
    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Optional<Song> findById(long id) {
        return songRepository.findById(id);
    }

    @Override
    public void delete(Song song) {
        songRepository.delete(song);
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public Page<Song> findByName(Pageable pageable, String name) {
        return songRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Song> findByCategory(Pageable pageable, String category) {
        return songRepository.findAllByCategoryContaining(pageable, category);
    }
}
