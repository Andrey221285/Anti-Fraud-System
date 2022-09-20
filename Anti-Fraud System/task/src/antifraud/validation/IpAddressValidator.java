package antifraud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IpAddressValidator implements ConstraintValidator<IpConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() ==0){
            return false;
        }
        String [] arr = value.split("\\.");
        if (arr.length< 4){
            return false;
        }

        for (String s  : arr){
            try {
                int i = Integer.parseInt(s);
                if (i<0 || i>255){
                    return false;
                }
            } catch (Exception e) {
               return false;
            }
        }

        return true;
    }
}