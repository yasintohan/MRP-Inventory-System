
#  MRP Inventory System <img src="http://img.shields.io/badge/-Java-F89820?style=flat&logo=java&logoColor=white">

I developed a computer program that takes bill of material and the gross requirements of the end item along with the stock, scheduled receipt, arrival on week, lead time and lot sizing rule information of the subcomponents to create MRP records for all parts and components of the snow shovel.

## Product Structure
Consider the product structure that belongs to a snow shovel. The demand and the inventory data provided below:
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/graphic.jpg">
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/table.jpg">

## Class Diagram
There are two classes in the system. The class named "Item" allows us to keep the information of each product. The class named "mainClass" is used to store the information in the "Item" class by assigning the information to the sub-classes and to make calculations and print the information for each sub-item.
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/console1.jpg">


## System Operation
When the system is turned on, a screen opens that offers options to make a selection first and calls the necessary methods according to the selected process.
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/ClassDiagram.jpg">
**"Print Inventory" option**
The "Print Inventory" option lists the amount of "Item" classes stored in the system in the inventory as shown in the figure.
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/inventory.jpg">
**"Order Item" Option**
1. The item number is taken as input from the user.
2. If the selected item exists in the system, the item entered as input is assigned as the current item. If there are no items in the system, the 1st stage will be returned.
3. The number of products that the user requests one by one for each week is requested as input.
4. The current item and weekly request list are assigned to the "calculate" method and the method is returned.
5. Calculations for the entered item are returned and assigned to the "Item" class.
6. Calculations are printed on the screen in tabular form.
7. Sub-item test is done for the current item, and if there are sub-items, they are re-inserted into the method and again from the 5th stage.
8. After the method is returned for each item, the system returns to the selection screen
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/example.jpg">
**"Show Items" Option**
All items defined in the system are printed on the screen in tree form.
<img src = "https://github.com/yasintohan/MRP-Inventory-System/blob/master/itemsTree.jpg">


by [Yasin Tohan](https://github.com/yasintohan)