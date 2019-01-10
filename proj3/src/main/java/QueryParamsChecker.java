import java.util.Map;

public class QueryParamsChecker {
    public static boolean isValid(Map<String, Double> params) {
        double lrlon = params.get("lrlon");
        double ullon = params.get("ullon");
        double lrlat = params.get("lrlat");
        double ullat = params.get("ullat");

        // Check whether the coordinates make sense
        if (ullon > lrlon || ullat < lrlat) {
            return false;
        }

        // Check whether completely out of range
        if (longitudeOutOfBound(ullon) && longitudeOutOfBound(lrlon) && latitudeOutOfBound(ullat) && latitudeOutOfBound(lrlat)) {
            return false;
        }
        return true;
    }

    private static boolean longitudeOutOfBound(double lon) {
        if (lon < MapServer.ROOT_ULLON || lon > MapServer.ROOT_LRLON) {
            return true;
        }
        return false;
    }

    private static boolean latitudeOutOfBound(double lat) {
        if (lat > MapServer.ROOT_ULLAT || lat < MapServer.ROOT_LRLAT) {
            return true;
        }
        return false;
    }
}
