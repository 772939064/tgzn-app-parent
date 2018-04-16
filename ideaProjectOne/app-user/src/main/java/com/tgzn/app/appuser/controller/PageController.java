package com.tgzn.app.appuser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController
 *
 * @author Yarn
 * @date 2018-04-09 11:13
 **/
@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("{url}")
    public String goUrl(@PathVariable String url){
        return url;
    }
}
