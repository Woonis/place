package sample.wooni.place.common;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ValueConvertUtils {
    private static final String ADDRESS_PATTERN = "(([가-힣A-Za-z·\\d~\\-.]{2,}([로길]).\\d+)|([가-힣A-Za-z·\\d~\\-.]+([읍동])\\s)[\\-\\d]+)";
    public static String convertTitle(String value) {
        return value.trim().replaceAll("<[^>]*>", StringUtils.EMPTY);
    }

    public static String convertAddress(String address) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);

        if (matcher.find()) {
            return matcher.group();
        }

        return address;
    }
}
