package backend._Service;

import backend._Model.Classes;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface SClass {
    Iterable<Classes> findAll();
    Optional<Classes> findById(Long id);
}
