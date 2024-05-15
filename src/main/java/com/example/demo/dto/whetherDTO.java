package com.example.demo.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class whetherDTO {
    public Timelines timelines;
    public Location location;
}
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    class Daily{
        public Date time;
        public Values values;
    }

    class Hourly{
        public Date time;
        public Values values;
    }

    public class Location{
        public double lat;
        public double lon;
    }

    class Minutely{
        public Date time;
        public Values values;
    }
    

    class Timelines{
        public ArrayList<Minutely> minutely;
        public ArrayList<Hourly> hourly;
        public ArrayList<Daily> daily;
    }

    class Values{
        public double cloudBase;
        public double cloudCeiling;
        public double cloudCover;
        public double dewPoint;
        public int freezingRainIntensity;
        public double humidity;
        public int precipitationProbability;
        public double pressureSurfaceLevel;
        public int rainIntensity;
        public int sleetIntensity;
        public int snowIntensity;
        public double temperature;
        public double temperatureApparent;
        public int uvHealthConcern;
        public int uvIndex;
        public int visibility;
        public int weatherCode;
        public double windDirection;
        public double windGust;
        public double windSpeed;
        public double evapotranspiration;
        public int iceAccumulation;
        public int iceAccumulationLwe;
        public double rainAccumulation;
        public double rainAccumulationLwe;
        public int sleetAccumulation;
        public int sleetAccumulationLwe;
        public int snowAccumulation;
        public int snowAccumulationLwe;
        public int snowDepth;
        public double cloudBaseAvg;
        public double cloudBaseMax;
        public double cloudBaseMin;
        public double cloudCeilingAvg;
        public double cloudCeilingMax;
        public double cloudCeilingMin;
        public double cloudCoverAvg;
        public double cloudCoverMax;
        public double cloudCoverMin;
        public double dewPointAvg;
        public double dewPointMax;
        public double dewPointMin;
        public double evapotranspirationAvg;
        public double evapotranspirationMax;
        public double evapotranspirationMin;
        public double evapotranspirationSum;
        public int freezingRainIntensityAvg;
        public int freezingRainIntensityMax;
        public int freezingRainIntensityMin;
        public double humidityAvg;
        public double humidityMax;
        public double humidityMin;
        public int iceAccumulationAvg;
        public int iceAccumulationLweAvg;
        public int iceAccumulationLweMax;
        public int iceAccumulationLweMin;
        public int iceAccumulationLweSum;
        public int iceAccumulationMax;
        public int iceAccumulationMin;
        public int iceAccumulationSum;
        public Date moonriseTime;
        public Date moonsetTime;
        public double precipitationProbabilityAvg;
        public int precipitationProbabilityMax;
        public int precipitationProbabilityMin;
        public double pressureSurfaceLevelAvg;
        public double pressureSurfaceLevelMax;
        public double pressureSurfaceLevelMin;
        public double rainAccumulationAvg;
        public double rainAccumulationLweAvg;
        public double rainAccumulationLweMax;
        public int rainAccumulationLweMin;
        public double rainAccumulationMax;
        public int rainAccumulationMin;
        public double rainAccumulationSum;
        public double rainIntensityAvg;
        public double rainIntensityMax;
        public int rainIntensityMin;
        public int sleetAccumulationAvg;
        public int sleetAccumulationLweAvg;
        public int sleetAccumulationLweMax;
        public int sleetAccumulationLweMin;
        public int sleetAccumulationLweSum;
        public int sleetAccumulationMax;
        public int sleetAccumulationMin;
        public int sleetIntensityAvg;
        public int sleetIntensityMax;
        public int sleetIntensityMin;
        public int snowAccumulationAvg;
        public int snowAccumulationLweAvg;
        public int snowAccumulationLweMax;
        public int snowAccumulationLweMin;
        public int snowAccumulationLweSum;
        public int snowAccumulationMax;
        public int snowAccumulationMin;
        public int snowAccumulationSum;
        public int snowDepthAvg;
        public int snowDepthMax;
        public int snowDepthMin;
        public int snowDepthSum;
        public int snowIntensityAvg;
        public int snowIntensityMax;
        public int snowIntensityMin;
        public Date sunriseTime;
        public Date sunsetTime;
        public double temperatureApparentAvg;
        public double temperatureApparentMax;
        public double temperatureApparentMin;
        public double temperatureAvg;
        public double temperatureMax;
        public double temperatureMin;
        public int uvHealthConcernAvg;
        public int uvHealthConcernMax;
        public int uvHealthConcernMin;
        public int uvIndexAvg;
        public int uvIndexMax;
        public int uvIndexMin;
        public double visibilityAvg;
        public double visibilityMax;
        public double visibilityMin;
        public int weatherCodeMax;
        public int weatherCodeMin;
        public double windDirectionAvg;
        public double windGustAvg;
        public double windGustMax;
        public double windGustMin;
        public double windSpeedAvg;
        public double windSpeedMax;
        public double windSpeedMin;
    }
