package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class RefreshDAOImpl implements RefreshDAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    private EntityManager entityManager;

    public RefreshDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void clean() {
        logger.info("Cleaning ...");

        // delete object with primary key
        Query theQuery =
                entityManager.createQuery("delete from Employee");

        theQuery.executeUpdate();

        logger.info("Cleaning complete.");
    }

    @Override
    public void insertData(List<Employee> employees) {
        logger.info("Insert employees ...");

        for (Employee tempEmployee : employees) {
            // set id to 0 to force insert
            tempEmployee.setId(0);

            logger.info(tempEmployee.toString());
            entityManager.persist(tempEmployee);
        }

        logger.info("Insert employees complete.");
    }

}
