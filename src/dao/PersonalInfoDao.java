/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Employee;

/**
 *
 * @author cleju
 */
public interface PersonalInfoDao {
    public Employee get(String username) throws Exception;
}
