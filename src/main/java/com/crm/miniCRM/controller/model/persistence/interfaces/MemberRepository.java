package com.crm.miniCRM.controller.model.persistence.interfaces;

import com.crm.miniCRM.controller.model.persistence.helpers.MemberID;
import com.crm.miniCRM.controller.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, MemberID> {
    Optional<Member> findById(MemberID id);

}