package pers.benj.service;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    List<Map> queryByAttribute(String attrName, String attrValue);
}
