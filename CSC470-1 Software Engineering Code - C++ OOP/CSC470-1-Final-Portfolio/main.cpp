#include <ctime>
#include <iostream>
#include <string>
#include <vector>
#include <iomanip>

using namespace std;

class Wheel
{
private:
    int wheelID;
    string wheelType;
    double wheelSize;
    string wheelTread;
    string wheelBrand;
    double basePrice;

public:
    Wheel(int wheelID, string wheelType, double size, string tread, string brand, double price)
    {
        this->wheelID = wheelID;
        this->wheelType = wheelType;
        this->wheelSize = size;
        this->wheelTread = tread;
        this->wheelBrand = brand;
        this->basePrice = price;
    }

    double getPrice() { return this->basePrice; }

    string getWheelType() { return this->wheelType; }

    double getWheelSize() { return this->wheelSize; }

    string getWheelTread() { return this->wheelTread; }

    string getWheelBrand() { return this->wheelBrand; }
};

class Engine
{
private:
    int engineID;
    string engineType;
    int cylinders;
    string category;
    int horsePower;
    double basePrice;

public:
    Engine(int engineID, string type, int cylinders, string category, int horsePower, double price)
    {
        this->engineID = engineID;
        this->engineType = type;
        this->cylinders = cylinders;
        this->category = category;
        this->horsePower = horsePower;
        this->basePrice = price;
    }

    Engine(){};

    Engine(const Engine& copy)
        : engineID{ copy.engineID }
        , engineType{ copy.engineType }
        , cylinders{ copy.cylinders }
        , category{ copy.category }
        , horsePower{ copy.horsePower }
        , basePrice{ copy.basePrice }
    {
    }

    double getPrice() { return this->basePrice; }

    string getEngineType() { return this->engineType; }

    int getCylinders() { return this->cylinders; }

    string getCategory() { return this->category; }

    int getHorsePower() { return this->horsePower; }
};

class Body
{
private:
    int bodyID;
    string color;
    int numDoors;
    string bodyType;
    string vehicleClass;
    double basePrice;

public:
    Body(int bodyID, string color, int numDoors, string type, string vehicleClass, double price)
    {
        this->bodyID = bodyID;
        this->bodyType = type;
        this->color = color;
        this->numDoors = numDoors;
        this->vehicleClass = vehicleClass;
        this->basePrice = price;
    }

    Body() {}

    Body(const Body& copy)
        : bodyID{ copy.bodyID }
        , color{ copy.color }
        , numDoors{ copy.numDoors }
        , bodyType{ copy.bodyType }
        , vehicleClass{ copy.vehicleClass }
        , basePrice{ copy.basePrice }
    {
    }

    double getPrice() { return this->basePrice; }

    string getColor() { return this->color; }

    string getBodyType() { return this->bodyType; }

    int getNumDoors() { return this->numDoors; }

    string getVehicleClass() { return this->vehicleClass; }
};

class Vehicle
{
private:
    string vehicleID;
    vector<Wheel> wheels{};
    double vehiclePrice;
    Engine engine;
    Body body;

public:
    Vehicle(string vehicleID, double vehiclePrice, Engine engine, Body body)
    {
        this->vehicleID = vehicleID;
        this->vehiclePrice = vehiclePrice;
        this->engine = engine;
        this->body = body;
    };

    void addWheel(Wheel wheel)
    {
        this->wheels.push_back(wheel);
        vehiclePrice += wheel.getPrice();
    }

    double getVehiclePrice() { return this->vehiclePrice; }

    vector<Wheel> getWheels() { return this->wheels; }

    Engine getEngine() { return this->engine; }

    Body getBody() { return this->body; }
};

class Car : public Vehicle
{
private:
    bool hatchback;

public:
    Car(string vehicleID, Engine engine, Body body, bool hatch)
        : Vehicle{ vehicleID, 10000 + engine.getPrice() + body.getPrice(), engine, body } {};

    bool getHatchback() { return this->hatchback; }
};

class Truck : public Vehicle
{
private:
    double bedSize;

public:
    Truck(string vehicleID, Engine engine, Body body, double bedSize)
        : Vehicle{ vehicleID, 15000 + engine.getPrice() + body.getPrice(), engine, body }
    {
        this->bedSize = bedSize;
    };

    double getBedSize() { return this->bedSize; }
};

class Phone
{
private:
    int phoneID;
    int areaCode;
    int phoneNumber;
    string timeZone;
    bool primary;
    bool fax;

public:
    Phone(int phoneID, int areaCode, int phoneNumber, string timeZone, bool primary, bool fax)
    {
        this->phoneID = phoneID;
        this->areaCode = areaCode;
        this->phoneNumber = phoneNumber;
        this->timeZone = timeZone;
        this->primary = primary;
        this->fax = fax;
    }
    
    int getAreaCode() { return this->areaCode; }
    
    int getPhoneNumber() { return this->phoneNumber; }
    
    string getTimeZone() { return this->timeZone; }
    
    bool getPrimary() { return this->primary; }
    
    bool getFax() { return this->fax; }
};

class Address
{
private:
    int addressID;
    string street;
    string city;
    string state;
    string zip;

public:
    Address(int addressID, string street, string city, string state, string zip)
    {
        this->addressID = addressID;
        this->street = street;
        this->city = city;
        this->state = state;
        this->zip = zip;
    }
    
    string getStreet() { return this->street; }
    
    string getCity() { return this->city; }
    
    string getState() { return this->state; }
    
    string getZip() { return this->zip; }
};

class Driver
{
private:
    int driverID;
    vector<Address> addresses{};
    vector<Phone> phoneNumbers{};
    string firstName;
    string lastName;

public:
    Driver(int driverID, string firstName, string lastName)
    {
        this->driverID = driverID;
        this->firstName = firstName;
        this->lastName = lastName;
    }

    void addAddress(Address address) { this->addresses.push_back(address); }

    void addPhone(Phone phone) { this->phoneNumbers.push_back(phone); }
    
    vector<Address> getAddresses() { return this->addresses; }
    
    vector<Phone> getPhoneNumbers() { return this->phoneNumbers; }
    
    string getFirstName() { return this->firstName; }
    
    string getLastName() { return this->lastName; }
};

class TransactionBridge
{
private:
    int transactionID;
    vector<Driver> drivers{};
    vector<Vehicle> vehicles{};
    double baseTransactionAmount;
    time_t transactionDate;

public:
    TransactionBridge(int transactionID)
        : transactionDate{ time(0) }
    {
        this->transactionID = transactionID;
        this->baseTransactionAmount = 0;
    }

    void addDriver(Driver driver) { this->drivers.push_back(driver); }

    void addVehicle(Vehicle vehicle)
    {
        this->vehicles.push_back(vehicle);
        this->baseTransactionAmount += vehicle.getVehiclePrice();
    }

    double getBaseTransactionAmount() { return this->baseTransactionAmount; }
    
    vector<Driver> getDrivers() { return this->drivers; }
    
    vector<Vehicle> getVehicles() { return this->vehicles; }
    
    time_t getTransactionDate() { return this->transactionDate; }
    
    int getTransactionID() { return this->transactionID; }
};

int main()
{
    //preconditions two drivers and two vehicles
    Wheel wheelC1(1, "RF1", 15, "something", "wheelco", 100.50);
    Wheel wheelC2(2, "RF1", 15, "something", "wheelco", 100.50);
    Wheel wheelC3(3, "RF1", 15, "something", "wheelco", 100.50);
    Wheel wheelC4(4, "RF1", 15, "something", "wheelco", 100.50);
    Engine engine1(1, "C1", 4, "Hybrid", 200, 3000);
    Body body1(1, "White", 2, "C1B", "Basic", 2500);
    
    Wheel wheelT1(5, "ZX1", 20, "something", "wheelco", 200.99);
    Wheel wheelT2(6, "ZX1", 20, "something", "wheelco", 200.99);
    Wheel wheelT3(7, "ZX1", 20, "something", "wheelco", 200.99);
    Wheel wheelT4(8, "ZX1", 20, "something", "wheelco", 200.99);
    Engine engine2(9, "T1", 6, "Gasoline", 300, 5000);
    Body body2(2, "Metallic Gray", 4, "T1L", "Luxury", 4000);
    
    Car car1("C100", engine1, body1, true);

    car1.addWheel(wheelC1);
    car1.addWheel(wheelC2);
    car1.addWheel(wheelC3);
    car1.addWheel(wheelC4);

    Truck truck1("T100", engine2, body2, 6.5);
    
    truck1.addWheel(wheelT1);
    truck1.addWheel(wheelT2);
    truck1.addWheel(wheelT3);
    truck1.addWheel(wheelT4);
    
    Phone phone1(1, 602, 4444444, "Arizona", true, false);
    
    Address address1(1, "100 Example Street", "Phoenix", "AZ", "85000-1111");
    
    Driver newDriver1(1, "Someone", "Somewhere");
    
    newDriver1.addAddress(address1);
    newDriver1.addPhone(phone1);
    
    Phone phone2(2, 623 , 2222222, "Arizona", true, false);
    
    Address address2(2, "100 Example Street", "Phoenix", "AZ", "85000-1111");
    
    Driver newDriver2(2, "Anyone", "Anywhere");
    
    newDriver2.addAddress(address2);
    newDriver2.addPhone(phone2);
    
    TransactionBridge newTransaction(1);
    
    newTransaction.addVehicle(car1);
    newTransaction.addVehicle(truck1);
    newTransaction.addDriver(newDriver1);
    newTransaction.addDriver(newDriver2);
    //end Preconditions
    
    cout << fixed << setprecision(2);
    cout << "===============================" << endl;
    cout << "Transaction ID: " << newTransaction.getTransactionID() << endl;
    for (size_t i {}; i < newTransaction.getDrivers().size(); i++) {
        cout << "Customer: " << newTransaction.getDrivers().at(i).getFirstName() << " " << newTransaction.getDrivers().at(i).getLastName() << endl;
        }
    cout << "Base Transaction Amount:  $" << newTransaction.getBaseTransactionAmount() << endl;
    for (size_t i {}; i < newTransaction.getVehicles().size(); i++) {
        cout << "       " << i+1 << ". Vehicle Price:  $" << newTransaction.getVehicles().at(i ).getVehiclePrice() << endl;
        cout << "           " << "Engine Price:   $" << newTransaction.getVehicles().at(i).getEngine().getPrice() << endl;
        cout << "             " << "Body Price:   $" << newTransaction.getVehicles().at(i).getBody().getPrice() << endl;
        vector<Wheel> temp_wheels = newTransaction.getVehicles().at(i).getWheels();
        for (size_t i {}; i < temp_wheels.size() ; i++) {
            cout << "            " << "Wheel Price:    $" << temp_wheels.at(i).getPrice() << endl;
            }
        }
    cout << "===============================" << endl;


    return 0;
}