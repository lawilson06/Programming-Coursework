"""
ITS320 Final Portfolio Project Option 1 Dealership Inventory
Lawrence Wilson
"""

# Dealership class that handles inventory management of automobile objects for the frontend

from automobile import Automobile
from backend import ManageInventory

class Dealership:
    def __init__(self):
        self.inventory = []
        self.record_numbers = []
        self.existing_inventory = ManageInventory()
        if len(self.existing_inventory.get_inventory()) > 0:
            for item in self.existing_inventory.get_inventory():
                self.record_numbers.append(int(item[0]))
            self.vehicle_number = min(self.record_numbers)
            for item in self.existing_inventory.get_inventory():
                self.add_vehicle(item[1],item[2],item[3],int(item[4]),item[5],int(item[6]),float(item[7]))
            self.vehicle_number = max(self.record_numbers) + 1
        else: self.vehicle_number = 0


    def add_vehicle(self, vehicle_make, vehicle_model, vehicle_category, vehicle_year, vehicle_color,
                 vehicle_mileage, vehicle_price, vehicle_fees=0, vehicle_apr=9.95, payment_months=72):
        new_automobile_obj = Automobile(self.vehicle_number, vehicle_make, vehicle_model, vehicle_category,
                                        vehicle_year, vehicle_color, vehicle_mileage, vehicle_price, vehicle_fees,
                                        vehicle_apr, payment_months)
        self.vehicle_number += 1
        self.inventory.append(new_automobile_obj)

    def get_vehicles(self):
        return self.inventory

    def update_file(self):
        temp_array = []
        for vehicle in self.inventory:
            vehicle_record = (f"{vehicle.get_vehicle_id()},{vehicle.get_vehicle_make()},{vehicle.get_vehicle_model()},"
                              f"{vehicle.get_vehicle_category()},{vehicle.get_vehicle_year()},"
                              f"{vehicle.get_vehicle_color()},{vehicle.get_vehicle_mileage()},"
                              f"{vehicle.get_vehicle_price()}" + '\n')
            temp_array.append(vehicle_record)
            self.existing_inventory.set_inventory(temp_array)

    def remove_vehicle(self, veh_no):
        for index, vehicle in enumerate(self.inventory):
            if vehicle.get_vehicle_id() == veh_no:
                self.inventory.pop(index)
                break

    def update_vehicle(self, veh_no, vehicle_make, vehicle_model, vehicle_category, vehicle_year, vehicle_color,
                 vehicle_mileage, vehicle_price):
        for index, vehicle in enumerate(self.inventory):
            if vehicle.get_vehicle_id() == veh_no:
                vehicle.set_vehicle_make(vehicle_make)
                vehicle.set_vehicle_model(vehicle_model)
                vehicle.set_vehicle_category(vehicle_category)
                vehicle.set_vehicle_year(vehicle_year)
                vehicle.set_vehicle_color(vehicle_color)
                vehicle.set_vehicle_mileage(vehicle_mileage)
                vehicle.set_vehicle_price(vehicle_price)
                break

    def estimate_payment_vehicle(self, veh_no, fees, months, apr):
        for index, vehicle in enumerate(self.inventory):
            if vehicle.get_vehicle_id() == veh_no:
                vehicle.set_vehicle_payment(vehicle.retrieve_estimate(fees, months, apr))
                return vehicle.get_vehicle_payment_estimate()