"""
ITS320 Final Portfolio Project Option 1 Dealership Inventory
Lawrence Wilson
"""

# Automobile class for individual vehicle objects

from automobile_price import AutomobilePrice

class Automobile:
    def __init__(self, vehicle_id, vehicle_make, vehicle_model, vehicle_category, vehicle_year, vehicle_color,
                 vehicle_mileage, vehicle_price, vehicle_fees, vehicle_apr, payment_months):
        self.__vehicle_id = vehicle_id
        self.__vehicle_make = vehicle_make
        self.__vehicle_model = vehicle_model
        self.__vehicle_category = vehicle_category
        self.__vehicle_year = vehicle_year
        self.__vehicle_color = vehicle_color
        self.__vehicle_mileage = vehicle_mileage
        self.__vehicle_price = vehicle_price
        self.__vehicle_price_obj = AutomobilePrice(vehicle_price, vehicle_fees)
        self.__vehicle_total_price = self.__vehicle_price_obj.get_price()
        self.__vehicle_payment_estimate = self.__vehicle_price_obj.calculate_estimate_payment(vehicle_apr,payment_months)

    def retrieve_estimate(self, fees, months, apr):
        self.__vehicle_price_obj.set_price(self.__vehicle_price, fees)
        return self.__vehicle_price_obj.calculate_estimate_payment(apr,months)

    def get_vehicle_id(self):
        return self.__vehicle_id

    def get_vehicle_make(self):
        return self.__vehicle_make

    def get_vehicle_model(self):
        return self.__vehicle_model

    def get_vehicle_category(self):
        return self.__vehicle_category

    def get_vehicle_year(self):
        return self.__vehicle_year

    def get_vehicle_color(self):
        return self.__vehicle_color

    def get_vehicle_mileage(self):
        return self.__vehicle_mileage

    def get_vehicle_price(self):
        return self.__vehicle_price

    def get_vehicle_total_price(self):
        return self.__vehicle_total_price

    def get_vehicle_payment_estimate(self):
        return self.__vehicle_payment_estimate

    def set_vehicle_make(self, make):
        self.__vehicle_make = make

    def set_vehicle_model(self, model):
        self.__vehicle_model = model

    def set_vehicle_category(self, category):
        self.__vehicle_category = category

    def set_vehicle_year(self, year):
        self.__vehicle_year = year

    def set_vehicle_color(self, color):
        self.__vehicle_color = color

    def set_vehicle_mileage(self, miles):
        self.__vehicle_mileage = miles

    def set_vehicle_price(self, price):
        self.__vehicle_price = price

    def set_vehicle_payment(self, estimate):
        self.__vehicle_payment_estimate = estimate
