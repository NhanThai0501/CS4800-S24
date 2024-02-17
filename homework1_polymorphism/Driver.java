/**
 * 
 */
package homework1_polymorphism;

/**
 * @author NhanThai
 *
 */
public class Driver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Ship[] ships = new Ship[3];

        ships[0] = new Ship();
        ships[0].setName("Hector");
        ships[0].setYearBuilt("2015");

        ships[1] = new CruiseShip();
        ships[1].setName("Carnival Horizon");
        ships[1].setYearBuilt("2016");
        ((CruiseShip) ships[1]).setMaxPassengers(3960); 

        ships[2] = new CargoShip();
        ships[2].setName("Namura Queen");
        ships[2].setYearBuilt("2020");
        ((CargoShip) ships[2]).setCargoCapacity(47146); 

        for (Ship ship : ships) {
            ship.details();
            System.out.println();
        }
    }

}
