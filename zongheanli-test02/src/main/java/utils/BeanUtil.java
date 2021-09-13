package utils;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class BeanUtil {
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public static <T> T processParameters(HttpServletRequest request, Class<T> targetClass) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        T instance = targetClass.newInstance();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);

            if(name.equals("option")){
                continue;
            }

            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, targetClass);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            Class<?>[] parameterTypes = writeMethod.getParameterTypes();
            if (parameterTypes.length > 0) {
                Class<?> parameterType = parameterTypes[0];
                if(parameterType== Date.class){
                    if (writeMethod != null) {
                        Date date = simpleDateFormat.parse(value);
                        writeMethod.invoke(instance,date);
                    }
                    continue;
                }

                if(parameterType==String.class){
                    if(writeMethod!=null){
                        writeMethod.invoke(instance,value);
                    }
                    continue;
                }

                if(parameterType==Long.class||parameterType==long.class){
                    if(writeMethod!=null){
                        long l = Long.parseLong(value);
                        writeMethod.invoke(instance,l);
                    }
                    continue;
                }

                if(parameterType==Integer.class||parameterType==int.class) {
                    if(writeMethod!=null){
                        int i = Integer.parseInt(value);
                        writeMethod.invoke(instance,i);
                    }
                    continue;
                }


            }

        }
        return instance;
    }
}
