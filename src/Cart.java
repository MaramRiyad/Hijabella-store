import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Cart {
 private  ArrayList<Product> itemsList;
 

    public Cart() {
        itemsList = new ArrayList<>(); 
    }

    public  void addProduct(Product product) {//بدك تربطيها بزر السلة يلي عند كل منتج
        itemsList.add(product);
    }

    public void removeProduct(Product product) {//بحس لازم نحط زر و هي الامر تبعه جنب البرودكت لما ينضاف للسلة 
        itemsList.remove(product);
    }

    public  ArrayList<Product> getitems() {
        return itemsList;
    }

    public double getTotal() {
        double total = 0;
        for (Product product : itemsList) {//بنلف على كل السلة و منجمع الاسعار 
            total += product.getPrice();
        }
        return total;
    }

    public void saveToFiles() {//الفاتورة 
        String fileName = "bill.txt";//الفايل يلي بدها تتخزن فيه الفاتورة 
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("Your purchase invoice");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//فورمات للتاريخ و الاوقت و النظام يلي بيمشو عليه انه 24 ساعة و عدد الخانات و هيك 
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Riyadh"));
            writer.println("Date and time of purchase: " + now.format(formatter));
            writer.println();
            writer.println("Items:");
            writer.println("--------------------------------------");

            ArrayList<String> processedNameStrings = new ArrayList<>();//
           // String nameString = p.getName();

            for (int i = 0; i < itemsList.size(); i++) {//لفة عالقائمة عشان نشوف المنتجات المتكررة 
                Product p = itemsList.get(i);
                String nameString = p.getName();

                if (processedNameStrings.contains(nameString)) { // بس اذا في منتج متكرر بكمل بالكود هاد 
                    continue;
                }

                int qty = 1;
                for (int j = i + 1; j < itemsList.size(); j++) {
                    if (itemsList.get(j).getName().equals(nameString)) {
                        qty++;
                    }
                }

                processedNameStrings.add(nameString);//بتضيف المنتج لقائمة المتكررات

                double price = p.getPrice();
                double total = price * qty;

                writer.println(nameString + " | Price: " + price + " | Quantity: " + qty + " | Total: " + total);
            }
        
            writer.println("--------------------------------------");
            writer.println("Total bill: " + getTotal());
            writer.println("Thank you for shopping with us!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}