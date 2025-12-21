package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface RefreshDAO {

    void clean();

    void insertData(List<Employee> employees);

}
