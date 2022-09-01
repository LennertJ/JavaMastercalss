package com.crm.miniCRM.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class ErrorControllerImpl implements ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Error</title>\n" +
                "  <style>\n" +
                "\n" +
                "    .page_404{ padding:40px 0; background:#fff; font-family: 'Arvo', serif;\n" +
                "    }\n" +
                "\n" +
                "    .page_404  img{ width:100%;}\n" +
                "\n" +
                "    .four_zero_four_bg{\n" +
                "\n" +
                "      background-image: url(https://cdn.dribbble.com/users/285475/screenshots/2083086/dribbble_1.gif);\n" +
                "      height: 400px;\n" +
                "      background-position: center;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    .four_zero_four_bg h1{\n" +
                "      font-size:80px;\n" +
                "    }\n" +
                "\n" +
                "    .four_zero_four_bg h3{\n" +
                "      font-size:80px;\n" +
                "    }\n" +
                "\n" +
                "    .link_404{\n" +
                "      color: #fff!important;\n" +
                "      padding: 10px 20px;\n" +
                "      background: #39ac31;\n" +
                "      margin: 20px 0;\n" +
                "      display: inline-block;}\n" +
                "    .contant_box_404{ margin-top:-50px;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<section class=\"page_404\">\n" +
                "  <div class=\"container\">\n" +
                "    <div class=\"row\">\n" +
                "      <div class=\"col-sm-12 \">\n" +
                "        <div class=\"col-sm-10 col-sm-offset-1  text-center\">\n" +
                "          <div class=\"four_zero_four_bg\">\n" +
                "            <h1 class=\"text-center \">404</h1>\n" +
                "\n" +
                "\n" +
                "          </div>\n" +
                "\n" +
                "          <div class=\"contant_box_404\">\n" +
                "            <h3 class=\"h2\">\n" +
                "              Look like you're lost\n" +
                "            </h3>\n" +
                "\n" +
                "            <p>the page you are looking for not avaible!</p>\n" +
                "\n" +
                "            <a href=\"\\ \" class=\"link_404\">Go to Home</a>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
