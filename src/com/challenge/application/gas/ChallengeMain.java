package com.challenge.application.gas;

public class ChallengeMain {
    private static int RouteLength;
    private static final String impossible = "impossible";

    public static void main(String[] args) {
        String[] route = new String[]{"4", "3:1", "2:2", "1:2", "0:1"};
        System.out.println("got circular route : " + getCircularRoute(route));
    }

    public static Object getCircularRoute(String[] gasStations) {
        int length = Integer.parseInt(gasStations[0]);

        outloop:
        for (int i = 1; i < gasStations.length; i++) {
            double remainingFuel = 0;
            GasStation gasStation = new GasStation(gasStations[i]);
            remainingFuel += gasStation.getFuelDifference();
            if (remainingFuel < 0)
                continue;
            int j = i + 1;

            if (j == length + 1)
                j = 1;

            while (i != j) {
                gasStation = new GasStation(gasStations[j]);
                remainingFuel += gasStation.getFuelDifference();
                if (remainingFuel < 0)
                    continue outloop;
                j++;
                if (j == length + 1)
                    j = 1;
            }
            return i;
        }
        return impossible;

    }


}
