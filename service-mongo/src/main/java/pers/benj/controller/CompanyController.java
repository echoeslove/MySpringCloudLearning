package pers.benj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.benj.service.CompanyService;

@RestController
@RequestMapping("/mongo-service")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/find/attribute")
    public List<Map> queryByAttribute(@RequestParam("attrName") String attrName,
                    @RequestParam("attrValue") String attrValue) {
        return companyService.queryByAttribute(attrName, attrValue);
    }
}
