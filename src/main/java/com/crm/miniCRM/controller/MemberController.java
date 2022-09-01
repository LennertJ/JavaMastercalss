package com.crm.miniCRM.controller;

import com.crm.miniCRM.dto.MemberDto;
import com.crm.miniCRM.dto.PersonDto;
import com.crm.miniCRM.model.Member;
import com.crm.miniCRM.model.Person;
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
    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String getmembers(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();
        members.forEach(m -> memberDtos.add(convertToDto(m)));
        model.addAttribute("members", memberDtos);
        return "members";
    }

    protected MemberDto convertToDto(Member entity) {
        return new MemberDto(entity.getId(), entity.getSince(),entity.getUntil());
    }
}
