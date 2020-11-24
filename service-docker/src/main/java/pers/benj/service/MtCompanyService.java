package pers.benj.service;

import java.util.List;

import pers.benj.entity.MtCompanyEntity;

public interface MtCompanyService {

    List<MtCompanyEntity> getAllCompany();

    List<MtCompanyEntity> findCompanyByAttribute(String attrName,String attrValue);
}
