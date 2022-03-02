package backend.service.imp;

import backend.model.Human;
import backend.repository.IHumanRepository;
import backend.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HumanService implements IHumanService {
    @Autowired
    IHumanRepository humanRepository;
    @Override
    public Page<Human> findAll(Pageable pageable) {
        return humanRepository.findAll(pageable);
    }

    @Override
    public Page<Human> findByName(Pageable pageable, String name) {
        return humanRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Optional<Human> findById(long id) {
        return humanRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        humanRepository.deleteById(id);
    }

    @Override
    public void save(Human human) {
        humanRepository.save(human);
    }


}
