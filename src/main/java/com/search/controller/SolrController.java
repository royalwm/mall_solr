package com.search.controller;

import com.search.entity.ResultSearch;
import com.search.service.SolrSerive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/solr")
public class SolrController {
    @Autowired
    private SolrSerive solrSerive;
    @Value("${solr.page.size}")
    private int pageSize;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public int addIndex(HttpServletRequest request) {
        return solrSerive.addData(request);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(HttpServletRequest request,String keyword,@RequestParam(defaultValue="1") Integer page,Model model) {
        ResultSearch search = solrSerive.search(request,keyword, page, pageSize);
        model.addAttribute("query",keyword);
        model.addAttribute("page",page);
        search.setPageSize(pageSize);
        model.addAttribute("totalPages",search.getPage());
        model.addAttribute("itemList",search.getItemList());
        model.addAttribute("recourdCount",search.getTotal());
        return "index";
    }
}
