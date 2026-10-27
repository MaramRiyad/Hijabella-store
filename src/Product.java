public class Product {
    private String category;
    private String name;
    private String imageName;
    private double price;
    private String origin;

    public Product(String category, String name, String imageName, double price, String origin) {
        this.category = category;
        this.name = name;
        this.imageName = imageName;
        this.price = price;
        this.origin = origin;
    }

    public String getCategory() {
    	return category;
    	}
    
    public String getName() { 
    	return name;
    	}
    
    public String getImageName() {
    	return imageName; 
    	}
    
    public double getPrice() {
    	return price; 
    	}
    
    
    public String getOrigin() {
    	return origin; 
    	}
}


