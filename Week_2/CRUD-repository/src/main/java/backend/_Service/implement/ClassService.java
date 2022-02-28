package backend._Service.implement;

import backend._Model.Classes;
import backend._Repository.IClassRepository;
import backend._Service.SClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService implements SClass {
    @Autowired
    private IClassRepository classRepository;
    @Override
    public Iterable<Classes> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classes> findById(Long id) {
        return classRepository.findById(id);
    }
}
