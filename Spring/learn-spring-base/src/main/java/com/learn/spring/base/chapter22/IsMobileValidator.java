package com.learn.spring.base.chapter22;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    private boolean require = false;
    public void initialize(IsMobile isMobile) {
        this.require = isMobile.required();
    }
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (require) {
            return isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return isMobile(s);
            }
        }
    }

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

}
