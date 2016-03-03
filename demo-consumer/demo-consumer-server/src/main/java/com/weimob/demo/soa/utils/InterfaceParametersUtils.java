package com.weimob.demo.soa.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class InterfaceParametersUtils {

    /**
     * 根据解决类名和方法名，获取接口参数
     * @param interfaceName
     * @param methodName
     * @return
     */
    public static String getInterfaceInputJsonString(String interfaceName,String methodName){
        Method[] methods;
        String jsonInput = null;
        try {
            methods = Class.forName(interfaceName).getDeclaredMethods();
            for (Method method : methods) {
                if(method.getName().equals(methodName)){
                    jsonInput = getMethodsParameterJson(method);
                }
            }
        } catch (Exception e) {
        }
        return jsonInput;
    }
    
    /**
     * 
     * @param packageName
     * @return
     * @throws IOException 
     */
    private static String getMethodsParameterJson(Method method) {       
        
        Type[] ts = method.getGenericParameterTypes();
        List<Object> list = new ArrayList<Object>();
        for(Type t:ts){ 
            
            list.add(createInstance(t));
        }
                
        return toJsonString(list);
        
    }
    
    /**
     * 根据类型创建实例
     * @param type
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Object createInstance(Type type){
        Object obj = createSimpleObject(type);
        if(type instanceof ParameterizedType){
            
            try{
                Type[] ts = ((ParameterizedType)type).getActualTypeArguments();
                if(ts.length==1){
                    Type trueType = ts[0];
                    ((List)obj).add(createInstance(trueType));
                }
                else if(ts.length==2){
                    Type keyType = ts[0];
                    Type valType = ts[1];
                    Object key = createInstance(keyType);
                    Object val = createInstance(valType);
                    ((Map)obj).put(key, val);
                }
            }
            catch(Exception e){
            }
        }       
                
        return obj;
    }
    
    
    private static Object createSimpleObject(Type clazz){
        Object o = null;
        //这里对性能要求不高，只要能生成一个对象即可
        try{
            o = JSON.parseObject("0", clazz);
            if(o instanceof String){
                return "";
            }
        }
        catch(Exception e){
            try{
                o = JSON.parseObject("[]", clazz);
            }
            catch(Exception ee){
                if( null == o ){
                    try{
                        
                        o = JSON.parseObject("{}", clazz);
                    }
                    catch(Exception e1){
                    }
                }
            }
        }
        return o;
    }
    
    private static String toJsonString(Object obj){
        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.QuoteFieldNames, true);
        serializer.setDateFormat("yyyy-MM-dd HH:mm:ss");
        serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
        serializer.config(SerializerFeature.PrettyFormat, true);
        serializer.config(SerializerFeature.WriteMapNullValue, true);
        serializer.config(SerializerFeature.WriteNullNumberAsZero, true);
        serializer.config(SerializerFeature.WriteNullStringAsEmpty, true);
        serializer.config(SerializerFeature.WriteNullListAsEmpty, true);
        serializer.write(obj);
        
        String jsonString = out.toString();
        out.close();
        return jsonString;
    }
}
