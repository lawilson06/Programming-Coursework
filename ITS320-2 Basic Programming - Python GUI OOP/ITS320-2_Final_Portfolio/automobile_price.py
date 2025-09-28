"""
ITS320 Final Portfolio Project Option 1 Dealership Inventory
Lawrence Wilson
"""

# Automobile price class that takes in all parts of the payment; can calculated estimated monthly payments

from math import ceil

class AutomobilePrice:
    def __init__(self,vehicle_price, est_fees):
        self.__vehicle_price = vehicle_price
        self.__est_fees = est_fees
        self.__total_price = self.__calculate_price()

    def __calculate_price(self):
        return self.__vehicle_price + self.__est_fees

    def calculate_estimate_payment(self, apr, months):
        rate = (apr/100)/12
        return ceil((self.__total_price * rate) / (1-(1 + rate)**(-1*months)))

    def set_price(self, price, fees):
        self.__vehicle_price = price
        self.__est_fees = fees
        self.__total_price = self.__calculate_price()

    def get_price(self):
        return self.__total_price

