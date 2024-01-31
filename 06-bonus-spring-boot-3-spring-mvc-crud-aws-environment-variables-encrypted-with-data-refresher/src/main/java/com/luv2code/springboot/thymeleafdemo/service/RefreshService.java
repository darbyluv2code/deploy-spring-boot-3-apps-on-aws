package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.config.DemoConfig;
import com.luv2code.springboot.thymeleafdemo.dao.RefreshDAO;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.logging.Logger;

@Service
@EnableScheduling
public class RefreshService {

    private Logger logger = Logger.getLogger(getClass().getName());

    private RefreshDAO refreshDAO;

    private List<Employee> employees;

    @Value("${refresh.cron.expression}")
    private String cronExpression;

    public RefreshService(RefreshDAO refreshDAO, DemoConfig demoConfig) {
        this.refreshDAO = refreshDAO;
        employees = demoConfig.getEmployees();
    }

    @PostConstruct
    void dump() {
        logger.info("List of employees loaded via DemoConfig: " + employees);
        logger.info("We'll refresh these employees based on our cron expression: " + cronExpression);
    }

    @Transactional
    @Scheduled(cron="${refresh.cron.expression}")
    public void run() {

        refreshDAO.clean();

        refreshDAO.insertData(employees);
    }

}
