public class Main {

    private static void printErrorExit(String message) {
        System.err.println(message);
        System.exit(1);
    }

    public static void main(String[] args) throws IllegalArgumentException {
        // we need at least four arguments
        if (args.length < 4)
            printErrorExit("Usage: ./Java Main [length of street cells] " +
                    "[amount of cars on street] [decimal probability number] " +
                    "[time interval between simulation steps] [optional: amount of steps]");

        //1st argument: length of freeway (in slots)
        int freewayLength = Integer.parseInt(args[0]);
        //2nd argument: amount of cars on freeway
        int carsAmount = Integer.parseInt(args[1]);
        //3rd argument: value of random number probability limit
        double probabilityLimit = Double.parseDouble(args[2]);
        //4th argument: time interval in milliseconds
        long intervalms = Long.parseLong(args[3]);

        if (freewayLength < 1)
            printErrorExit("Need a street of length bigger than 0");
        else if (freewayLength < carsAmount || carsAmount < 0)
            printErrorExit("Amount of cars has to be a positive number " +
                    "that is equal to or less than street length");
        else if (probabilityLimit < 0 || probabilityLimit > 1)
            printErrorExit("Random limit needs to be a decimal number between 0 and 1");
        else if (intervalms < 0)
            printErrorExit("Time interval needs to be a positive integer");

        Street freeway = new Street(freewayLength);
        freeway.insertCars(carsAmount);
        if (args.length == 4) {
            freeway.simulateTraffic(probabilityLimit, intervalms);
        } else {
            //5th argument (optional): number of simulation steps
            int simSteps = Integer.parseInt(args[4]);
            if (simSteps < 0)
                printErrorExit("Simulation step amount needs to be a positive integer");
            freeway.simulateTraffic(probabilityLimit, intervalms, simSteps);
        }
            
    }
}