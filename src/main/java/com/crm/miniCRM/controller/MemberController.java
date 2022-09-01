package com.crm.miniCRM.controller;

import com.crm.miniCRM.dto.MemberDto;
import com.crm.miniCRM.dto.PersonDto;
import com.crm.miniCRM.model.Member;
import com.crm.miniCRM.model.Person;
import com.crm.miniCRM.model.persistence.helpers.MemberID;
import com.crm.miniCRM.model.persistence.interfaces.MemberRepository;
import com.crm.miniCRM.model.persistence.interfaces.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/members")
public class MemberController {
    private final MemberRepository memberService;
    private final PersonRepository personService;

    public MemberController(MemberRepository memberService, PersonRepository personService) {
        this.memberService = memberService;
        this.personService = personService;
    }

    @GetMapping
    public String getmembers(Model model) {
        Iterable<Member> members = memberService.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();
        members.forEach(m-> {
            boolean communityExists = false;
            MemberID memberID = m.getId();
            Long personId = memberID.getPerson_ID();
            Person person = this.personService.findById(personId).orElse(null);
            if(person ==null){
                return;
            }
            Long communityId = memberID.getCommunity_ID();
            for (MemberDto memberdto: memberDtos) {
                if(memberdto.getCommunityId()==communityId){
                    memberdto.addPersonToCommunity(person);
                    communityExists = true;
                }
            }
            if(!communityExists){
                memberDtos.add(new MemberDto(communityId, person, m.getSince(),m.getUntil()));
            }

        });
        model.addAttribute("members", memberDtos);
        return "members";
    }
}
