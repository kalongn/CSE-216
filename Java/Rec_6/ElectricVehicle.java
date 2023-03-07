package Java.Rec_6;

class ElectricVehicle extends Vehicle {
    double charge = 0.0; // percentage of battery charged, between 0 and 100
    
    public ElectricVehicle(String name) {
      super(name);
      this.charge = 100; // new instances are fully charged
    }
    
    @Override public void start() {
      System.out.println("Starting electric vehicle " + name + ".");
    }
    
    public void drive() {
      System.out.println("Driving electric vehicle " + name + ".");
    }
    
    public void stop() {
      System.out.println("Stopping electric vehicle " + name + ".");
    }
    
    public double getCharge() { return this.charge; }
    
    public static void main(String[] args) { // entry point of any java program.
      Vehicle v1 = new Vehicle("Truck");
      ElectricVehicle v2 = new ElectricVehicle("Tesla");
      //We are assigning a Child object to a variable of type Parent.
      Vehicle v3 = new ElectricVehicle("Leaf");
      v1.start();
      v2.start();
      v3.start();
      v2.getCharge();
      // This error since we treat this as a Vehicle and Vehicle does not have getCharge() method.
      // v3.getCharge();
    }
  }
  