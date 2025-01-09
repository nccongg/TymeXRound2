#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Product
{
private:
    string name;
    double price;
    int quantity;

public:
    Product()
    {
        name = "";
        price = 0;
        quantity = 0;
    }
    Product(string _name, double _price, int _quantity)
    {
        name = _name;
        price = double(_price);
        quantity = _quantity;
    }
    string getName()
    {
        return name;
    }
    double getPrice()
    {
        return price;
    }
    int getQuantity()
    {
        return quantity;
    }
    void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }
};

class Inventory
{
private:
    vector<Product> products;

public:
    Inventory(){

    }

    void addProduct(Product product){
        for(auto& existingProduct : products){
            if(existingProduct.getName() == product.getName()) {
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                return;
            }
        }
        products.push_back(product);
    }
    double calTotalValue(){
        double total = 0;
        for(auto product : products) {
            total += product.getQuantity() * product.getPrice();
        }
        return total;
    }

    string findMostExpProduct(){
        string mostExpProduct = "Empty!";
        double maxPrice = 0;
        for(auto product : products){
            if(product.getPrice() > maxPrice){
                mostExpProduct = product.getName();
                maxPrice = product.getPrice();
            }
        }
        return mostExpProduct;
    }

    bool isProductInStock(string productName){
        for(auto product : products) {
            if(product.getName() == productName)
                return (product.getQuantity() > 0);
        }
        return false;
    }

    void sortProductsByPrice(bool ascending = true)
    {
        sort(products.begin(), products.end(), [ascending](Product &a, Product &b)
             { return ascending ? a.getPrice() < b.getPrice() : a.getPrice() > b.getPrice(); });
    }

    void sortProductsByQuantity(bool ascending = true)
    {
        sort(products.begin(), products.end(), [ascending](Product &a,  Product &b)
             { return ascending ? a.getQuantity() < b.getQuantity() : a.getQuantity() > b.getQuantity(); });
    }

    void displayProducts() const
    {
        for (auto product : products){
            cout << "Name: " << product.getName() << ", Price: " << product.getPrice() << ", Quantity: " << product.getQuantity() << '\n';
        }
    }
};

int main()
{
    Inventory inventory;

    inventory.addProduct(Product("Laptop", 999.5, 5));
    inventory.addProduct(Product("Smartphone", 499, 10));
    inventory.addProduct(Product("Tablet", 299, 0));
    inventory.addProduct(Product("Smartwatch", 199, 3));
    inventory.addProduct(Product("Smartphone", 499, 2)); // Updating quantity

    cout << "Total inventory value: " << inventory.calTotalValue() << '\n';
    cout << "The most expensive product: " << inventory.findMostExpProduct() << '\n';
    cout << "Is a product named Laptop in stock: " << (inventory.isProductInStock("Laptop") ? "true" : "false") << '\n';

    cout << "\nSort product in descending with price:\n";
    inventory.sortProductsByPrice(false);
    inventory.displayProducts();

    cout << "\nSort product in ascending with quantity:\n";
    inventory.sortProductsByQuantity(true);
    inventory.displayProducts();

    return 0;
}