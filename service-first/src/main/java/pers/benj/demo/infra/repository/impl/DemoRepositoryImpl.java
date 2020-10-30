package pers.benj.demo.infra.repository.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import pers.benj.demo.domain.entity.Person;
import pers.benj.demo.domain.repository.DemoRepository;

/**
 * @author benjamin
 */
@Component
public class DemoRepositoryImpl implements DemoRepository {

    private final List<Person> DEMO_PERSON_LIST = Arrays.asList(new Person("A", 1), new Person("B", 2));

    @Override
    public Person getMaxAgePerson() {
        Optional<Person> maxOpt = DEMO_PERSON_LIST.stream().max(Comparator.comparingInt(Person::getAge));

        return maxOpt.orElse(null);

    }
}
