"""
ITS320 Final Portfolio Project Option 1 Dealership Inventory
Lawrence Wilson
"""

import os

# Read-Write Backend Operations File

class ManageInventory:
    def __init__(self):
        self.__filepath = 'dealer_inventory.txt'
        self.__no_file()

    def __no_file(self):
        if not os.path.exists('dealer_inventory.txt'):
            with open('dealer_inventory.txt', 'w') as file:
                pass

    def get_inventory(self):
        vehicle_attributes = []
        with open(self.__filepath, 'r') as existing_file:
            for line in existing_file:
                attributes = line.strip().split(',')
                vehicle_attributes.append(attributes)
        return vehicle_attributes

    def set_inventory(self, new_inventory):
        with open(self.__filepath, 'w') as existing_file:
            existing_file.writelines(new_inventory)

