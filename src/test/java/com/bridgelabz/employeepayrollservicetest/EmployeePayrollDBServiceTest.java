package com.bridgelabz.employeepayrollservicetest;

import com.bridgelabz.employeepayrollservice.EmployeePayrollDBService;
import com.bridgelabz.employeepayrollservice.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayrollDBServiceTest
{
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount()
    {
        EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertEquals(5, employeePayrollDataList.size());
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldnotMatchEmployeeCount()
    {
        EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertNotSame(4, employeePayrollDataList.size());
    }

    @Test
    public void givenNewEmployeeSalaryShouldUpdateWithDatabase()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollService
                .readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeBasic_pay("Terisa", 30000001.00);
        boolean result = employeePayrollService.checkEmployeePayrollSyncWithDB("Terisa");
        Assertions.assertTrue(result);
    }

    @Test
    public void givenDataRangeWhenRetrievedShouldMatchEmployeeCount()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.retrieveEmployeesForGivenDataRange("2018-01-01", "2019-01-03");
        Assertions.assertEquals(3, employeePayrollData.size());
    }

    @Test
    public void givenPayrollData_WhenAverageSalaryRetrieveByGender_ShouldReturnValue()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Map<String, Double> averageSalaryByGender = employeePayrollService.readAverageSalaryByGender(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertTrue(averageSalaryByGender.get("M").equals(8000000.0) && averageSalaryByGender.get("F").equals(26666686.186666667));
    }

    @Test
    public void givenPayrollData_WhenSumSalaryRetrieveByGender_ShouldReturnValue()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Map<String, Double> sumSalaryByGender = employeePayrollService.readSumSalaryByGender(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertTrue(sumSalaryByGender.get("M").equals(24000000) && sumSalaryByGender.get("F").equals(80000058.56));
    }

    @Test
    public void givenPayrollData_WhenMinimumSalaryRetrieveByGender_ShouldReturnValue()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Map<String, Double> minimumSalaryByGender = employeePayrollService.readMinimumSalaryByGender(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertTrue(minimumSalaryByGender.get("M").equals(1000000) && minimumSalaryByGender.get("F").equals(20000056.56));
    }

    @Test
    public void givenPayrollData_WhenMaximumSalaryRetrieveByGender_ShouldReturnValue() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Map<String, Double> maximumSalaryByGender = employeePayrollService.readMaximumSalaryByGender(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertTrue(maximumSalaryByGender.get("M").equals(20000000) && maximumSalaryByGender.get("F").equals(30000001));
    }

    @Test
    public void givenPayrollData_WhenCountNameRetrieveByGender_ShouldReturnValue()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Map<String, Integer> countNameByGender = employeePayrollService.readCountNameByGender(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertTrue(countNameByGender.get("M").equals(3) && countNameByGender.get("F").equals(3));
    }

    @Test
    public void givenNewEmployee_whenAdded_ShouldSyncWithDB()
    {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.addEmployeeToPayroll("Sanika", "Sales","F", 400000.00, LocalDate.now());
        boolean result = employeePayrollService.checkEmployeePayrollSyncWithDB("Sanika");
        Assertions.assertTrue(result);
    }
}
