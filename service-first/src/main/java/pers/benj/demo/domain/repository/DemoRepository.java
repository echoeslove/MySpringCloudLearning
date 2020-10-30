package pers.benj.demo.domain.repository;

import pers.benj.demo.domain.entity.Person;

/**
 * @author benjamin
 */
public interface DemoRepository {

    /**
     * get max element
     * 
     * @author benjamin
     * @date 2020/10/30 3:41 PM
     * @return Person
     */
    Person getMaxAgePerson();
}
