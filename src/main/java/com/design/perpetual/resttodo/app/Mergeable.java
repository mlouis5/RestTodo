/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app;

/**
 *
 * @author Mac
 */
public interface Mergeable<T> {
    
    boolean merge(T t);
}
