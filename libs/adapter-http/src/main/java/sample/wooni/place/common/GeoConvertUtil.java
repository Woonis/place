package sample.wooni.place.common;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class GeoConvertUtil {
    public static GeoDetailDto convert(String x, String y) {
        double coorX = Double.parseDouble(x);
        double coorY = Double.parseDouble(y);

        GeoPoint in_pt = new GeoPoint(coorX, coorY);
        System.out.println("geo in : xGeo="  + in_pt.getX() + ", yGeo=" + in_pt.getY());
        GeoPoint tm_pt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, in_pt);
        System.out.println("tm : xTM=" + tm_pt.getX() + ", yTM=" + tm_pt.getY());
        GeoPoint katec_pt = GeoTrans.convert(GeoTrans.TM, GeoTrans.KATEC, tm_pt);
        System.out.println("katec : xKATEC=" + katec_pt.getX() + ", yKATEC=" + katec_pt.getY());

        return new GeoDetailDto(BigDecimal.valueOf(katec_pt.getX()), BigDecimal.valueOf(katec_pt.getY()));
    }
    public record GeoDetailDto(
            BigDecimal x,
            BigDecimal y
    ){}
}
