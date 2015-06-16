/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.pojos;

import com.design.perpetual.resttodo.app.entities.HouseholdMember;
import com.design.perpetual.resttodo.app.entities.Todo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HouseholdMemberDTO {
    
    private List<HouseholdMember> householdMembers;
    
    public HouseholdMemberDTO(){}
    
    public void setHouseholdMembers(List<HouseholdMember> members){
        this.householdMembers = members;
    }
    
    public List<HouseholdMember> getHouseholdMembers(){
        return householdMembers;
    }
    
    public List<Todo> getAllTodos(){
        final List<Todo> allTodos = new ArrayList();
        householdMembers.forEach((HouseholdMember hm) -> {
            allTodos.addAll(hm.getTodoList());
        });
        return allTodos;
    }
}
