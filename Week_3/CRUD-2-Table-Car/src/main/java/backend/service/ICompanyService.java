package backend.service;

import backend.model.Company;

import java.util.Optional;

public interface ICompanyService {
    Iterable<Company> findAll();
    Optional<Company> findById(long id);
    Iterable<Company> findAllById(long id);
}
