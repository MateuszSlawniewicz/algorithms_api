package com.pixel.algorithms_api.services;

import com.pixel.algorithms_api.model.Coordinate;
import com.pixel.algorithms_api.model.NNACoordinatesList;
import com.pixel.algorithms_api.model.NNACoordinatesListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NearestNeighbourAlgorithmServiceImplTest {

    @Autowired
    private NearestNeighbourAlgorithmService nearestNeighbourAlgorithmServiceImpl;
    private final Coordinate LODZ = new Coordinate("Lodz", 51.7833, 19.4667);
    private final Coordinate GDANSK = new Coordinate("Gdansk", 54.35, 18.6667);
    private final Coordinate TORUN = new Coordinate("Torun", 53.0137500, 18.5981400);
    private final Coordinate CIESZYN = new Coordinate("Cieszyn", 39.9075000, 116.3972300);
    private List<Coordinate> COORDINATES = new ArrayList<>();
    private final NNACoordinatesList ROAD_TO_CHECK = new NNACoordinatesList();
    private final Integer DISTANCE = 14308;


    {
        COORDINATES.add(LODZ);
        COORDINATES.add(CIESZYN);
        COORDINATES.add(GDANSK);
        COORDINATES.add(TORUN);
        ROAD_TO_CHECK.setCoordinates(COORDINATES);
    }

    @Test()
    void shouldTorunBeSecondOnRoad() {
        //when
        NNACoordinatesListDTO roadWithNNA = nearestNeighbourAlgorithmServiceImpl.findRoadWithNNA(ROAD_TO_CHECK);
        //then
        assertEquals(TORUN, roadWithNNA.getCoordinates().get(1));
    }

    @Test()
    void shouldCieszynBeLastCity() {
        //when
        NNACoordinatesListDTO roadWithNNA = nearestNeighbourAlgorithmServiceImpl.findRoadWithNNA(ROAD_TO_CHECK);
        //then
        assertEquals(CIESZYN, roadWithNNA.getCoordinates().get(roadWithNNA.getCoordinates().size()-1));
    }


    @Test()
    void shouldReturnCorrectDistance() {
        //when
        NNACoordinatesListDTO roadWithNNA = nearestNeighbourAlgorithmServiceImpl.findRoadWithNNA(ROAD_TO_CHECK);
        //then
        assertEquals(DISTANCE, roadWithNNA.getDistance().intValue());

    }
}
