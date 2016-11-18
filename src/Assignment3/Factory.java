package Assignment3;

public class Factory {

    private FoodItem[] foodBuffer;

    public void initFoodItems (){
        foodBuffer = new FoodItem[15];
        foodBuffer [0] = new FoodItem("Milk", 0.5, 1.1);
        foodBuffer [1] = new FoodItem("Cream", 0.1, 0.6);
        foodBuffer [2] = new FoodItem("Butter",0.66, 2.34);
        foodBuffer [3] = new FoodItem("Apple", 0.78, 1.0);
        foodBuffer [4] = new FoodItem("Ham", 1.5, 2.1);
        foodBuffer [5] = new FoodItem("Juice", 2.0, 2.1);
        foodBuffer [6] = new FoodItem("Yogurt", 1.5, 1.6);
        foodBuffer [7] = new FoodItem("Coffee", 0.66, 0.78);
        foodBuffer [8] = new FoodItem("Beer", 5.0, 6.1);
        foodBuffer [9] = new FoodItem("Sugar", 5.5, 6.0);
        foodBuffer [10] = new FoodItem("Salt", 0.12, 0.56);
        foodBuffer [11] = new FoodItem("Jam", 0.5, 0.63333);
        foodBuffer [12] = new FoodItem("Honey", 1.5, 2.0);
        foodBuffer [13] = new FoodItem("Flower", 3.0, 3.0);
        foodBuffer [14] = new FoodItem("Chicken", 10.0, 11.12);
        foodBuffer [15] = new FoodItem("Meat", 5.56, 6.0);
    }

}
