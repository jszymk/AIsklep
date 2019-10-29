package app.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class DtoUtils {

    public static <T> T convert(Object entity, Class<T> dtoClass) {
        if(entity == null) return null;
        try {
            T dto = dtoClass.newInstance();
            Map<String, Object> entityFields = getPropertyMap(entity);
            for(Field f: dtoClass.getDeclaredFields()) {
                if(entityFields.containsKey(f.getName())) {
                    setPrivateField(dto, f, entityFields.get(f.getName()));
                }
            }
            return dto;
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    private static Map<String, Object> getPropertyMap(Object entity) {
        if(entity == null) return Collections.emptyMap();
        else return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> getPrivateField(entity, f) != null)
                .collect(Collectors.toMap(Field::getName, f -> getPrivateField(entity, f)));
    }

    private static Object getPrivateField(Object o, Field f) {
        f.setAccessible(true);
        try {
            Object value = f.get(o);
            f.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }

    private static void setPrivateField(Object o, Field f, Object v) {
        f.setAccessible(true);
        try {
            f.set(o, v);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
        f.setAccessible(false);
    }

}
