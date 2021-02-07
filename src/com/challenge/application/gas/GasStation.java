package com.challenge.application.gas;

public class GasStation {

    private double amountOfGas;
    private double gasNeedNextStation;

    public GasStation(String gasStationRoute){
        String [] values = gasStationRoute.split(":");
        setAmountOfGas(Double.parseDouble(values[0]));
        setGasNeedNextStation(Double.parseDouble(values[1]));

    }

    public double getAmountOfGas() {
        return amountOfGas;
    }
    public double getFuelDifference() {
        return this.getAmountOfGas() - this.getGasNeedNextStation();
    }

    public void setAmountOfGas(double amountOfGas) {
        this.amountOfGas = amountOfGas;
    }

    public double getGasNeedNextStation() {
        return gasNeedNextStation;
    }

    public void setGasNeedNextStation(double gasNeedNextStation) {
        this.gasNeedNextStation = gasNeedNextStation;
    }
}
