package fodiee.thenick.com.foodiee;

public class CityName {

    String cityName;

    double latitude;
    double longitude;


    public CityName(String cityName,double latitude,double longitude)
    {
        this.cityName=cityName;
        this.latitude=latitude;
        this.longitude=longitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
