package com.szu.refrigerator.util;

import java.lang.reflect.Field;

public class MyObjectUtil {


    public static void copyParentFieldWithSaveName(Object source,Object target){

        for (Field sourceField : source.getClass().getDeclaredFields()) {

            for (Field targetField : target.getClass().getSuperclass().getDeclaredFields()) {

                /**
                 * 字段名相同就浅拷贝字段
                 */
                if(sourceField.getName().equals(targetField.getName())){
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    try {
                        targetField.set(target,sourceField.get(source));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        }
    }
}
