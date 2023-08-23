package com.example.prog4;

import com.example.prog4.repository.cnaps.CnapsEmployeeRepository;
import com.example.prog4.repository.employee.EmployeeRepository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private CnapsEmployeeRepository cnapsEmployeeRepository;


}
