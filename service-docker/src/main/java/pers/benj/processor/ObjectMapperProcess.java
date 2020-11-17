package pers.benj.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperProcess implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectMapper.class.equals(bean.getClass())){

            SimpleModule simpleModule = new SimpleModule();

            simpleModule.addSerializer(Long.class,new IdSerializer(Object.class));

            ObjectMapper mapper = (ObjectMapper) bean;

            mapper.registerModules(simpleModule);
        }
        return bean;
    }
}
