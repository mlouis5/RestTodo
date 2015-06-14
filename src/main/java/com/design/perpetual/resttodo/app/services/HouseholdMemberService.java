/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.services;

import com.design.perpetual.resttodo.app.pojos.HouseholdMemberDTO;
import com.design.perpetual.resttodo.app.repositories.HouseholdMemberRepo;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class HouseholdMemberService {
    
    @Autowired
    private HouseholdMemberRepo repo;
    
    @Autowired
    private ObjectFactory<HouseholdMemberDTO> beanFactory;
    
    public HouseholdMemberDTO getHouseholdMembers(){
        HouseholdMemberDTO hmd = beanFactory.getObject();
        hmd.setHouseholdMembers(repo.findAll());
        return hmd;
    }
}
