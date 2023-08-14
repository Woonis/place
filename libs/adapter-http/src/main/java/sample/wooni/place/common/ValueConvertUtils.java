package sample.wooni.place.common;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
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

    public static ValueConvertUtils.GeoDetailDto convert(String x, String y) {
        double coorX = Double.parseDouble(x);
        double coorY = Double.parseDouble(y);

        GeoPoint in_pt = new GeoPoint(coorX, coorY);
        GeoPoint tm_pt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, in_pt);
        GeoPoint katec_pt = GeoTrans.convert(GeoTrans.TM, GeoTrans.KATEC, tm_pt);

        return new ValueConvertUtils.GeoDetailDto(BigDecimal.valueOf(katec_pt.getX()), BigDecimal.valueOf(katec_pt.getY()));
    }
    public record GeoDetailDto(
            BigDecimal x,
            BigDecimal y
    ){}
}
