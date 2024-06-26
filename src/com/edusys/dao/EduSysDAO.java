/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import java.util.List;

/**
 *
 * @author admin
 */
public abstract class EduSysDAO<E, K> {

    abstract public void insert(E entity);
    abstract public void update(E entity);
    abstract public void delete(K key);
    abstract public List selectAll();
    abstract public E selectByID(K key);
    abstract protected List<E> selectBySQL(String sql, Object...args);
}
