package backend.repository;

import backend.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends PagingAndSortingRepository<Song, Long> {
    Page<Song> findAllByNameContaining(Pageable pageable, String name);
    Page<Song> findAllByCategoryContaining(Pageable pageable, String category);
}
