package com.pixel.algorithms_api.services;

import com.pixel.algorithms_api.model.Coordinate;
import com.pixel.algorithms_api.model.NNACoordinatesList;
import com.pixel.algorithms_api.model.NNACoordinatesListDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NearestNeighbourAlgorithmServiceImpl implements NearestNeighbourAlgorithmService {

    private Map<Double, Coordinate> distances = new HashMap<>();
    private NNACoordinatesListDTO listToResult;

    @Override
    public NNACoordinatesListDTO findRoadWithNNA(NNACoordinatesList coordinatesList) {
        listToResult = new NNACoordinatesListDTO();
        listToResult.setDistance(0.0);
        List<Coordinate> roadList = new ArrayList<>();
        roadList.add(coordinatesList.getCoordinates().get(0));
        List<Coordinate> coordinates = coordinatesList.getCoordinates();
        Optional<Coordinate> optionalFirstCoordinate = coordinates.stream().findFirst();
        optionalFirstCoordinate.ifPresent(o -> {
            coordinatesList.getCoordinates().add(o);
            coordinates.remove(o);
            findNearestPoint(o, coordinates);
        });
        return listToResult;
    }


    private void findNearestPoint(Coordinate coordinate, List<Coordinate> coordinates) {
        coordinates.forEach(coord -> calculateDistance(coordinate, coord));
        List<Double> temp = new ArrayList<>();
        Set<Map.Entry<Double, Coordinate>> entries = distances.entrySet();
        entries.forEach(e -> temp.add(e.getKey()));
        Collections.sort(temp);
        Double keyToNearestPlace = temp.get(0);
        Coordinate coordinate2 = distances.get(keyToNearestPlace);
        coordinates.remove(coordinate2);
        if (coordinates.size() > 0) {
            listToResult.setDistance(listToResult.getDistance() + keyToNearestPlace);
            listToResult.getCoordinates().add(coordinate2);
            if (coordinates.size() >= 1) {
                distances = new HashMap<>();
            }
            findNearestPoint(coordinate2, coordinates);

        } else {
            distances.keySet().stream().findFirst().ifPresent(b -> {
                Coordinate coordinate1 = distances.get(b);
                listToResult.getCoordinates().add(coordinate1);
                listToResult.setDistance(listToResult.getDistance() + b + computeDistance(coordinate1, listToResult.getCoordinates().get(0)));
            });
        }
    }


    private void calculateDistance(Coordinate coordinate1, Coordinate coordinate2) {
        double distance = computeDistance(coordinate1, coordinate2);
        distances.put(distance, coordinate2);
    }

    private double computeDistance(Coordinate coordinate1, Coordinate coordinate2) {
        double lat1 = coordinate1.getLat();
        double lng1 = coordinate1.getLng();
        double lat2 = coordinate2.getLat();
        double lng2 = coordinate2.getLng();

        double p = 0.017453292519943295;
        double a = 0.5 - Math.cos((lat2 - lat1) * p) / 2 + Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lng2 - lng1) * p)) / 2;
        return 12742 * Math.asin(Math.sqrt(a));
    }


}
