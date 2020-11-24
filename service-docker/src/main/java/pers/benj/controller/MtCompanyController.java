package pers.benj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.benj.entity.MtCompanyEntity;
import pers.benj.service.MtCompanyService;

@RestController
@RequestMapping("/postgres-demo")
public class MtCompanyController {

    @Autowired
    private MtCompanyService mtCompanyService;

    @GetMapping("/all")
    public List<MtCompanyEntity> getAllCompany() {
        return mtCompanyService.getAllCompany();
    }

    @GetMapping("/find/attribute")
    public List<MtCompanyEntity> findCompanyByAttribute(@RequestParam("attrName") String attrName,
                    @RequestParam("attrValue") String attrValue) {
        return mtCompanyService.findCompanyByAttribute(attrName, attrValue);
    }
}
