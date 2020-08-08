package com.learn.springboot.chapter2.http.message;

import com.learn.springboot.chapter2.entity.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

/**
 *  扩展
 *  自描述消息处理
 *
 * @ClassName PropertiesPersonHttpMessageConverter
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/7/25 16:13
 * @Version 1.0
 **/
public class PropertiesPersonHttpMessageConverter
         extends AbstractHttpMessageConverter<Person> {
    /**
     * 指定类型
     */
    public PropertiesPersonHttpMessageConverter() {
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    /**
     * 必须是Person类型/子类
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    /**
     *
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Person readInternal(Class<? extends Person> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream inputStream=inputMessage.getBody();
        Properties properties=new Properties();
        //请求内容转换为Properties
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
       //将properties内容转换到Person对象
        Person person=new Person();
        person.setId(Long.valueOf(properties.getProperty("person.id")));
        person.setName(properties.getProperty("person.name"));
        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream=outputMessage.getBody();
        Properties properties=new Properties();
        properties.setProperty("person.id",String.valueOf(person.getId()));
        properties.setProperty("person.name",person.getName());
        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"Write By Server");
    }
}
