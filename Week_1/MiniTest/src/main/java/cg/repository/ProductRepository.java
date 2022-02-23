package cg.repository;

import cg.model.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
@Repository
public class ProductRepository implements IProductRepository{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Product> findAll() {
        String QUERY_SELECT_ALL = "SELECT p FROM Product AS p";
        TypedQuery<Product> findAllQuery = entityManager.createQuery(QUERY_SELECT_ALL, Product.class);
        return (ArrayList<Product>) findAllQuery.getResultList();
    }
    @Override
    public Product findProductById(int id) {
        String QUERY_SELECT_BY_ID = "SELECT p FROM Product AS p WHERE p.id = :id ";
        TypedQuery<Product> findByIdQuery = entityManager.createQuery(QUERY_SELECT_BY_ID, Product.class);
        findByIdQuery.setParameter("id", id);
        return findByIdQuery.getSingleResult();
    }

    @Override
    public ArrayList<Product> findByKeyword(String keyword) {
        String QUERY_SELECT_BY_KEYWORD = "SELECT p FROM Product AS p WHERE p.name LIKE :keyword";
        TypedQuery<Product> findByKeywordQuery = entityManager.createQuery(QUERY_SELECT_BY_KEYWORD, Product.class);
        findByKeywordQuery.setParameter("keyword", keyword);
        return (ArrayList<Product>) findByKeywordQuery.getResultList();
    }

    @Override
    public Product saveProduct(Product product) {
        Transaction transaction = null;
        Product origin;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            if (product.getId() != 0){
                origin = findProductById(product.getId());
                origin.setName(product.getName());
                origin.setPrice(product.getPrice());
                origin.setDescription(product.getDescription());
                origin.setImageURL(product.getImageURL());
            } else {
                origin = product;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return null;
    }


    @Override
    public Product deleteProduct(int id) {
        Transaction transaction = null;
        Product origin = findProductById(id);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (origin != null){
                session.delete(origin);
            }
            transaction.commit();
            return origin;
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return null;
    }
}
