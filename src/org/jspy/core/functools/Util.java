package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Util {

    public Map<String, Object> vars(Object obj) {
        Class<?> klass = obj.getClass();
        Map<String, Object> campos = new HashMap<>();

        Field[] fields = klass.getDeclaredFields();

        for (Field f : fields) {
            String chave = f.getName();
            Class<?> tipo = f.getType();
            Object valor = null;

            Constructor<?> construtor = null;
            try {
                // construtor = tipo.getConstructor(klass);
                Object valorObj = klass.newInstance();
                valor = f.get(valorObj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            campos.put(chave, valor);
        }

        return campos;
    }

    public Map<String, Object> var(Object obj) {
        Map<String, Object> campos = new HashMap<>();

        Object valor = null;

        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        for(Method m : methods) {
            if(m.getName().startsWith("get") && !m.getName().equals("getClass")) {
                String atributo = m.getName().substring(3).toLowerCase();

                try {
                    valor = m.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                campos.put(atributo, valor);
            }
        }

        System.out.println(campos);
        return campos;
    }

    public Set<String> dir(Object obj) {
        Set<String> atributos = new HashSet<>();

        Class<?> clazz = obj.getClass();
        Method[] metodos = clazz.getMethods();
        for(Method m : metodos){
            atributos.add(m.getName());
        }

        for(Method m : metodos){
            if(m.getName().startsWith("get")){
                String atributo = m.getName().substring(3).toLowerCase();
                atributos.add(atributo);
            }
        }

        return atributos;
    }

}
