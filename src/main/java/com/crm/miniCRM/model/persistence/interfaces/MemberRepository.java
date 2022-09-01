package com.crm.miniCRM.model.persistence.interfaces;

import com.crm.miniCRM.dto.MemberDto;
import com.crm.miniCRM.model.Member;
import com.crm.miniCRM.model.persistence.helpers.MemberID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, MemberID> {
    Optional<Member> findById(MemberID id);

}