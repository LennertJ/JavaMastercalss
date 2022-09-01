package com.crm.miniCRM.controller;

import com.crm.miniCRM.dto.CommunityDto;
import com.crm.miniCRM.model.Community;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @GetMapping
    public String home() {

        return "home";
    }
}
