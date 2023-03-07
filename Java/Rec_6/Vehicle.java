package Java.Rec_6;

class Vehicle {
    String name;
    // “this” keyword refers to the current instance
    public Vehicle(String name) { this.name = name; }
    
    public void start() {
      System.out.println("Starting vehicle " + this.name + ".");
    }
    
    public void drive() {
      System.out.println("Driving vehicle " + this.name + ".");
    }
    
    public void stop() {
      System.out.println("Stopping vehicle " + this.name + ".");
    }
  }
  