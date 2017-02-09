package com.wondersgroup.util.controller;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {

	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATEFORMAT2 = new  SimpleDateFormat("yyyyMMdd");
    private static final DateFormat DATEFORMAT3 = new  SimpleDateFormat("yyyy-MM");
    
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat TIMEFORMAT2 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final DateFormat TIMEFORMAT3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private static final DateFormat TIMEFORMAT4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            try {
                if(this.dateFormat != null)
                    setValue(this.dateFormat.parse(text));
                else {
                    if(text.contains("-") && !text.contains(":") && !text.contains(".") && text.length()==10)
                        setValue(DATEFORMAT.parse(text));
                    else if(!text.contains("-") && !text.contains(":") && text.length()==8)
                        setValue(DATEFORMAT2.parse(text));
                    else if(text.contains("-") && text.length()==7)
                        setValue(DATEFORMAT3.parse(text));
                    else if(!text.contains("-") && !text.contains(":") && !text.contains(" ") && !text.contains(".") && text.length()==14)
                        setValue(TIMEFORMAT2.parse(text));
                    else if(text.contains("."))
                        setValue(TIMEFORMAT3.parse(text));
                    else if(text.contains("-") && text.contains(":") && !text.contains(".") && text.length()==16)
                        setValue(TIMEFORMAT4.parse(text));
                    else
                        setValue(TIMEFORMAT.parse(text));
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if(dateFormat == null)
            dateFormat = TIMEFORMAT;
        return (value != null ? dateFormat.format(value) : "");
    }
}
