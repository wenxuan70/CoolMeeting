package org.javaboy.meeting.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateTimeConvert implements Converter<String, Date> {
    private static final ThreadLocal<SimpleDateFormat> sdfOne = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm"));

    private static final ThreadLocal<SimpleDateFormat> sdfTwo = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd"));

    @Override
    public Date convert(String source) {
        if (source == null || source.length() == 0)
            return null;
        try {
            if (source.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"))
                return sdfTwo.get().parse(source);
            else
                return sdfOne.get().parse(source.replace('T', ' '));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
