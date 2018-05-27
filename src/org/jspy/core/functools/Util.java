package org.jspy.core.functools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Util implements PyFuncs {

    @Override
    public Map<String, Object> vars(Object obj) {
        Map<String, Object> fields = new HashMap<>();

        Object value = null;

        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        for(Method m : methods) {
            if(m.getName().startsWith("get") && !m.getName().equals("getClass")) {
                String field = m.getName().substring(3).toLowerCase();

                try {
                    value = m.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                fields.put(field, value);
            }
        }

        return fields;
    }

    @Override
    public Set<String> dir(Object obj) {
        Set<String> fields = new HashSet<>();

        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            fields.add(m.getName());

            if(m.getName().startsWith("get") && !m.getName().equals("getClass")){
                String field = m.getName().substring(3).toLowerCase();
                fields.add(field);
            }
        }

        return fields;
    }

}
