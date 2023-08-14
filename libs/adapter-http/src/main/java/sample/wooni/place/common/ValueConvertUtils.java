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

        GeoPoint inPt = new GeoPoint(coorX, coorY);
        GeoPoint tmPt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, inPt);
        GeoPoint katecPt = GeoTrans.convert(GeoTrans.TM, GeoTrans.KATEC, tmPt);

        return new ValueConvertUtils.GeoDetailDto(BigDecimal.valueOf(katecPt.getX()), BigDecimal.valueOf(katecPt.getY()));
    }
    public record GeoDetailDto(
            BigDecimal x,
            BigDecimal y
    ){}
}
