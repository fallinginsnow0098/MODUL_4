package backend.service;

import backend.model.Smartphone;

public interface ISmartphoneService {

    Iterable<Smartphone> findAll();

    Smartphone findById(Integer id);

    Smartphone save(Smartphone phone);

    Smartphone remove(Integer id);
}
