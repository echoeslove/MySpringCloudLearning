package pers.benj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.benj.dao.MtCompanyDao;
import pers.benj.entity.MtCompanyEntity;
import pers.benj.service.MtCompanyService;

@Component
public class MtCompanyServiceImpl implements MtCompanyService {

    @Autowired
    private MtCompanyDao mtCompanyDao;

    @Override
    public List<MtCompanyEntity> getAllCompany() {
        return mtCompanyDao.findAll();
    }

    @Override
    public List<MtCompanyEntity> findCompanyByAttribute(String attrName, String attrValue) {
        return mtCompanyDao.findByAttribute(attrName, attrValue);
    }
}
