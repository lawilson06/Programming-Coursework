"""
ITS320 Final Portfolio Project Option 1 Dealership Inventory
Lawrence Wilson
"""

# GUI - Frontend File

from dealership import Dealership
from backend import ManageInventory
import FreeSimpleGUI as sg

dealership_archive = ManageInventory()
dealership_inventory = Dealership()
label_make = sg.Text('Enter the make')
input_make = sg.InputText(key='vehicle_make',s=(25,2),tooltip='Required')
label_model = sg.Text('Enter the model')
input_model = sg.InputText(key='vehicle_model',s=(25,2),tooltip='Required')
label_category = sg.Text('Select the category')
option_category = sg.OptionMenu(values=['SUV','Sedan','Truck','Minivan'],key='vehicle_category',s=(20,1),
                                tooltip='Required')
label_year = sg.Text('Enter the year')
input_year = sg.InputText(key='vehicle_year',s=(25,2),tooltip='Required')
label_color = sg.Text('Enter the color')
input_color = sg.InputText(key='vehicle_color',s=(25,2),tooltip='Required')
label_mileage = sg.Text('Enter the mileage')
input_mileage = sg.InputText(key='vehicle_mileage',s=(25,2),tooltip='Required')
label_price = sg.Text('Enter the price')
input_price = sg.InputText(key='vehicle_price',s=(25,2),tooltip='Required')
label_fees = sg.Text('Enter the fees')
input_fees = sg.InputText(key='vehicle_fees',s=(25,2),default_text='0',tooltip='Required')
label_months = sg.Text('Select the term')
option_months = sg.OptionMenu(['36','48','60','72'],s=(5,2),key='vehicle_months',default_value='72')
label_apr = sg.Text('Enter the APR')
input_apr = sg.InputText(key='vehicle_apr',s=(10,2),default_text='9.95')
button_add = sg.Button('Add Vehicle ⏩',key='Add',s=(15,2))
button_remove = sg.Button('Remove Vehicle ⏪',key='Remove',s=(15,2),button_color='IndianRed')
button_update = sg.Button('Update Vehicle ⏫',key='Update',s=(15,2))
button_populate = sg.Button('Populate Vehicle ♻',key='Populate',s=(15,2),button_color='Teal')
button_estimate = sg.Button('Estimate 💲',key='Estimate',s=(8,2),button_color='Olive')
label_payment = sg.Text('Estimated payment',visible=False,key='vehicle_payment_label')
read_payment = sg.InputText('',visible=False,key='vehicle_payment',readonly=True,s=(10,2),
                            disabled_readonly_background_color='grey')
label_spacer = sg.Text('')
label_current_inventory = sg.Text('Current Inventory')
inventory_list = sg.Listbox(values = dealership_archive.get_inventory(), key='vehicle_inventory',
                            enable_events=True)

col1 = sg.Column([[label_make],[input_make],[label_model],[input_model],[label_year],[input_year],
                  [label_price],[input_price]])
col2 = sg.Column([[label_color],[input_color],[label_mileage],[input_mileage],[label_category],[option_category],
                  [label_fees],[input_fees]])
col3 = sg.Column([[label_months],[option_months],[label_apr],[input_apr],[label_payment],[read_payment],
                  [button_estimate]])
col4 = sg.Column([[button_add],[button_update],[button_remove]])
col5 = sg.Column([[label_current_inventory],[inventory_list],[button_populate]])
window = sg.Window('ITS320 Final Portfolio - Dealer Inventory System',layout=[
    [col1,col2,col3,col4,col5]
])

def clear_fields():
    window['vehicle_make'].update('')
    window['vehicle_model'].update('')
    window['vehicle_year'].update('')
    window['vehicle_price'].update('')
    window['vehicle_color'].update('')
    window['vehicle_mileage'].update('')
    window['vehicle_category'].update('')

last_populated_record = ''

while True:
    event, values = window.read()
    match event:
        case 'Add':
            if values['vehicle_fees'] == '':
                window['vehicle_fees'].update('0')
            if values['vehicle_apr'] == '':
                window['vehicle_apr'].update('9.95')
            if (values['vehicle_make'] == '' or values['vehicle_model'] == '' or values['vehicle_year'] == '' or
                    values['vehicle_color'] == '' or values['vehicle_price'] == '' or values['vehicle_mileage'] == ''
                    or values['vehicle_category'] == ''):
                sg.popup_error("Must enter the vehicle's make, model, year, color, price, mileage, and category.")
            else:
                try:
                    dealership_inventory.add_vehicle(values['vehicle_make'],values['vehicle_model'],
                                                     values['vehicle_category'], int(values['vehicle_year']),
                                                     values['vehicle_color'], int(values['vehicle_mileage']),
                                                     float(values['vehicle_price']), float(values['vehicle_fees']),
                                                     float(values['vehicle_apr']), int(values['vehicle_months']))
                    dealership_inventory.update_file()
                    clear_fields()
                    window['vehicle_payment_label'].update(visible=False)
                    window['vehicle_payment'].update('', visible=False)
                    last_populated_record = ''
                    window['vehicle_inventory'].update(dealership_archive.get_inventory())
                    sg.popup('Vehicle was successfully added. 🚗')
                except ValueError:
                    sg.popup_error('Must enter a valid value')
        case 'Remove':
            try:
                dealership_inventory.remove_vehicle(int(values['vehicle_inventory'][0][0]))
                dealership_inventory.update_file()
                window['vehicle_inventory'].update(dealership_archive.get_inventory())
                clear_fields()
                window['vehicle_payment_label'].update(visible=False)
                window['vehicle_payment'].update('', visible=False)
                last_populated_record = ''
                sg.popup('Vehicle was successfully removed. ❎')
            except IndexError:
                sg.popup_error('Must select a vehicle to remove from the current inventory')
        case 'Populate':
            try:
                window['vehicle_make'].update(values['vehicle_inventory'][0][1])
                window['vehicle_model'].update(values['vehicle_inventory'][0][2])
                window['vehicle_category'].update(values['vehicle_inventory'][0][3])
                window['vehicle_year'].update(values['vehicle_inventory'][0][4])
                window['vehicle_color'].update(values['vehicle_inventory'][0][5])
                window['vehicle_mileage'].update(values['vehicle_inventory'][0][6])
                window['vehicle_price'].update(values['vehicle_inventory'][0][7])
                last_populated_record = int(values['vehicle_inventory'][0][0])
                window['vehicle_payment_label'].update(visible=False)
                window['vehicle_payment'].update('',visible=False)
            except IndexError:
                sg.popup_error('Must select a vehicle')
        case 'Update':
            if last_populated_record == '':
                sg.popup_error('Must first select a record to update and populate it.')
            else:
                if (values['vehicle_make'] == '' or values['vehicle_model'] == '' or values['vehicle_year'] == '' or
                        values['vehicle_color'] == '' or values['vehicle_price'] == '' or values['vehicle_mileage'] == ''
                        or values['vehicle_category'] == ''):
                    sg.popup_error("Must enter the vehicle's make, model, year, color, price, mileage, and category.")
                else:
                    try:
                        dealership_inventory.update_vehicle(last_populated_record,values['vehicle_make'],
                                                            values['vehicle_model'], values['vehicle_category'],
                                                            int(values['vehicle_year']), values['vehicle_color'],
                                                            int(values['vehicle_mileage']), float(values['vehicle_price']))
                        dealership_inventory.update_file()
                        window['vehicle_inventory'].update(dealership_archive.get_inventory())
                        clear_fields()
                        window['vehicle_payment_label'].update(visible=False)
                        window['vehicle_payment'].update('', visible=False)
                        sg.popup('Vehicle was successfully updated. ✅')
                        last_populated_record = ''
                    except ValueError:
                        sg.popup_error("Must enter valid values.")
        case 'Estimate':
            if last_populated_record == '':
                sg.popup_error('Must first select a record to estimate and populate it. '
                               'Update fees and apr if applicable.')
            else:
                try:
                    window['vehicle_payment_label'].update(visible=True)
                    window['vehicle_payment'].update(dealership_inventory.estimate_payment_vehicle(last_populated_record,
                                                                                        float(values['vehicle_fees']),
                                                                                        int(values['vehicle_months']),
                                                                                        float(values['vehicle_apr'])),
                                                     visible=True)
                except ValueError:
                    sg.popup_error('Must enter valid values.')
        case sg.WIN_CLOSED:
            break

window.close()
