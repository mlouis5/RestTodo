/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.repositories;

import com.design.perpetual.resttodo.app.entities.HouseholdMember;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class HouseholdMemberRepo extends AbstractRepository<HouseholdMember>{

    public HouseholdMemberRepo() {
        super(HouseholdMember.class);
    }
    
}
