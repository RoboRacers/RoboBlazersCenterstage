package com.roboracers.gaeldrive.sensors;

import org.apache.commons.math3.linear.RealVector;
import com.roboracers.gaeldrive.utils.Updatable;

public interface SensorModel extends Updatable {

    /**
     * Gets the weight modifier assigned to this model.
     * @return Weight modifier
     */
    public double getWeightModifier();

    /**
     * Returns a vectorized version of the reading.
     * @return Actual reading of the sensor
     */
    public RealVector getActualReading();

    /**
     * Returns the expected vectorized sensor reading from a particular state.
     * @param state State of the particle the reading is based on
     * @return Simulated sensor value
     */
    public RealVector getSimulatedReading(RealVector state);

}
