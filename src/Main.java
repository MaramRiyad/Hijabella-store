
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	Scene scene;
	static BorderPane mainPane;// خليتها ستاتك عشان ابقى استدعيها في كلاس واجهة تسجيل الدخول باسم هذا الكلاس
	Label titleLabel;
	Button backButton;
	Button CartButton;
	Button homeButton;
	Button informationButton;
	HBox mainiconsBox;
	
	  private Cart cart = new Cart();
    
	
	 PasswordField passwordField;// لاني فصلته هو والمتغير اللي في السطر اللي تحته في ميثود
	 TextField visiblePasswordField;
	 
	 TextField nameField;//  لاني استخدمها في الميثود تبعت الاكشن تبع كبسة اللوج إن
	 Label failLabel;// وهاي كمان لاني استخدمها في الميثود تبعت الاكشن تبع كبسة اللوج إن
	 
//===========================================================================================================================	 
	 
	 @Override
	public void start(Stage primaryStage) throws Exception {
  
		mainPane = new BorderPane();// حطيت نوعها هيك عشان اقدر اخلي ازرار ثابتة في القاع في كل الواجهات ورح نغير
									// البين اللي في السنتر
		// حجم البوردر بين وحجم اجزاءها بتغير على حسب الاشي اللي بنحطه فيها
		
		mainPane.setStyle("-fx-background-color: white;");
		scene = new Scene(mainPane);
		
		titleLabel = new Label(" ");//عشان يكون في امكانية اضافة عنوان في اي واجهة بدي اياها 
		titleLabel.setFont(Font.font("Century Schoolbook", FontWeight.BOLD,40));
		titleLabel.setStyle("-fx-text-fill:#f52a92;");
		
		// الكود اللي تحت عشان زر التراجع وضفته هان عشان كمان اقدر اضيفه في اي واجهة بدي اياها 
		Image image = new Image(getClass().getResourceAsStream("/images/back.jpeg"));
	    ImageView backImageView = new ImageView(image);
		backImageView.setFitWidth(40);
        backImageView.setFitHeight(30);

         backButton = new Button();
        backButton.setAlignment(Pos.BASELINE_LEFT);
        backButton.setPrefSize(30, 20);
        backButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");

        backButton.setGraphic(backImageView);
        
        
        
        Image cartImage = new Image(getClass().getResourceAsStream("/images/cart.png"));
	    ImageView cartImageView = new ImageView(cartImage);
	    cartImageView.setFitWidth(40);
	    cartImageView.setFitHeight(40);
        CartButton = new Button();
       // CartButton.setAlignment(Pos.BASELINE_LEFT);
        CartButton.setPrefSize(40, 40);
        CartButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");
        CartButton.setGraphic(cartImageView);
        

        Image homeImage = new Image(getClass().getResourceAsStream("/images/h.png"));
	    ImageView homeImageView = new ImageView(homeImage);
	    homeImageView.setFitWidth(40);
	    homeImageView.setFitHeight(40);
	    homeButton = new Button();
       // CartButton.setAlignment(Pos.BASELINE_LEFT);
	    homeButton.setPrefSize(40, 40);
	    homeButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");
	    homeButton.setGraphic(homeImageView);
	    homeButton.setOnAction(e->{
	    	showCategories();
	    });
	    
	    
	    Image informationImage = new Image(getClass().getResourceAsStream("/images/info.jpeg"));
	    ImageView informationImageView = new ImageView(informationImage);
	    informationImageView.setFitWidth(40);
	    informationImageView.setFitHeight(40);
	    informationButton = new Button();
       // CartButton.setAlignment(Pos.BASELINE_LEFT);
	    informationButton.setPrefSize(40, 40);
	    informationButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");
	    informationButton.setGraphic(informationImageView);
	    
	    informationButton.setOnAction(e->{
	    	showInfo();
	    });
	    
	     mainiconsBox = new HBox(200);
	    mainiconsBox.setAlignment(Pos.CENTER);
	    mainiconsBox.getChildren().addAll(informationButton,homeButton,CartButton);
	    //mainiconsBox.setStyle("-fx-border-color: #f52a92;");
	    mainPane.setBottom(mainiconsBox);
	    
	    CartButton.setOnAction(e->{
	    	showCart();
	    });
        
        // البوردر بين اللي تحت عشان انظم مواقع العنوان الرئيسي اللي في التوب وزر التراجع جنب بعض
        BorderPane topPane = new BorderPane();
        topPane.setStyle("-fx-background-color: white;");
        topPane.setPadding(new Insets(10));
        topPane.setPrefHeight(40); 
        
        // زر التراجع على اليسار
        topPane.setLeft(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);

        // العنوان في النص
        topPane.setCenter(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);



        mainPane.setTop(topPane);
		showStart();

		primaryStage.setTitle(" * Hijab Store * ");
		primaryStage.setScene(scene);
	    primaryStage.setMaximized(true);// عشان اول ما اشغل الكود تبين الشاشة كاملةً
		primaryStage.show();

	}

	 
//===========================================================================================================================	
	 
	private void showStart() {
		mainiconsBox.setVisible(false);
		titleLabel.setText("");
		backButton.setVisible(false);
		backButton.setAlignment(Pos.CENTER_LEFT);
		
		VBox startBox = new VBox(10);
//		Region spacer = new Region();
//		spacer.setMinHeight(20);// لتحديد الفراغ بين الصورة واعلى الواجهة

		startBox.setPadding(new Insets(0, 0, 0, 0));
		
		startBox.setAlignment(Pos.TOP_CENTER);
		Image image = new Image(getClass().getResource("/images/logo.jpeg").toString());
		ImageView logoImageView = new ImageView(image);
		logoImageView.setFitHeight(450);
		logoImageView.setFitWidth(500);

		Button startButton = new Button("Start Shopping");
		startButton.setMaxWidth(350);
		startButton.setMinHeight(30);
		startBox.getChildren().addAll(logoImageView, startButton);
		startButton.setFont(Font.font("Century Schoolbook", FontWeight.BOLD, 30));
		startButton.setStyle(
				"-fx-text-fill: #f52a92; -fx-background-color: #fffafd; -fx-border-color: #f52a92; -fx-border-width: 3;");
		
		startButton.setOnKeyPressed(e -> { // معنى هذا السطر ان لما نكبس على زر في الكيبورد
            if (e.getCode() == KeyCode.ENTER) { // هذا الشرط بده يتأكد ان الزر اللي كبسنا عليه هو الانتر
            	showLogin(); // وهان استدعيت الميثود تبعت لما اكبس على كبسة اللوج إن 
            }
        });
       
		
		
		//Platform.runLater(() -> startBox.requestFocus());// عشان ما نخلي التركيز اول ما نشغل الكود على الزر

		startButton.setOnAction(e -> {
			showLogin();
		});

		mainPane.setCenter(startBox);

	}
	
	
//===========================================================================================================================	
	

	private void showLogin() {
		mainiconsBox.setVisible(false);
		backButton.setVisible(true);
		titleLabel.setText("");
		
		backButton.setOnAction(e->{
			showStart();
		});
		
		
		VBox startPain = new VBox(25);
        startPain.setAlignment(Pos.TOP_CENTER);
        startPain.setStyle("-fx-background-color: #fffafd;");// الخلفية بيضا
        
//		Region spacer = new Region();
//        spacer.setMinHeight(25);

        Image logo = new Image("/images/loginimage.png");
        ImageView imageViewLogo = new ImageView(logo);
        
        imageViewLogo.setFitWidth(250);
        imageViewLogo.setFitHeight(250);
        
        Label titleLabel = new Label("Hijab clothing");
        titleLabel.setFont(Font.font("Century Schoolbook",FontWeight.BOLD,FontPosture.ITALIC, 40));
        titleLabel.setStyle("-fx-text-fill: #f52a92;");
        
//        Label loginLabel = new Label("Login");
//        loginLabel.setFont(Font.font("Century Schoolbook",FontWeight.BOLD,FontPosture.ITALIC, 28));
//        loginLabel.setStyle("-fx-text-fill: #f52a92;");


         nameField = new TextField();
        nameField.setMaxWidth(350);
        nameField.setMinHeight(40);
        nameField.setPromptText("User Name : ");
        nameField.setFont(Font.font("System", FontWeight.BOLD,20));
        nameField.setStyle("-fx-text-fill: black; -fx-background-color: #fffafd; -fx-border-color: #f52a92; -fx-border-width: 3;"); 
        
        
         passwordField = new PasswordField();
        passwordField.setMaxWidth(300);
        passwordField.setMinHeight(40);
        passwordField.setPromptText("password : ");
        passwordField.setFont(Font.font("System", FontWeight.BOLD,20));
        passwordField.setStyle("-fx-text-fill: black; -fx-background-color: #fffafd; -fx-border-color: #f52a92; -fx-border-width: 3;"); 
        


        StackPane imageContainer = new StackPane(imageViewLogo);
        imageContainer.setPadding(new Insets(1)); 
        imageContainer.setStyle("-fx-border-color: #f52a92; -fx-border-width: 3;");
        imageContainer.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // عشان نقدر نحط حدود للإمج فيوو
        
        
        Button loginButton = new Button("Log In ");
        loginButton.setMaxWidth(350);
        loginButton.setMinHeight(45);
        loginButton.setFont(Font.font("System", FontWeight.BOLD,20));
        loginButton.setStyle("-fx-text-fill: #f52a92; -fx-background-color: #fffafd; -fx-border-color: #f52a92; -fx-border-width: 3;"); 

        Image image = new Image(getClass().getResourceAsStream("/images/back.jpeg"));
        ImageView backImageView = new ImageView(image);
        

        startPain.getChildren().addAll( imageContainer,titleLabel,nameField,changePasswordVisibility(),loginButton);
        
        
        
        startPain.setOnMouseClicked(event -> {
            if (nameField.getText().isEmpty()|| passwordField.getText().isEmpty() ) {
            	startPain.requestFocus(); 
            }
        });
        // عشان اذا كان التركيز على صندوق نص محدد وكبست على الشاشة يروح التركيز عنه 
               
        
        Platform.runLater(() -> startPain.requestFocus());
        
        
         failLabel = new Label("The username or password is incorrect, try again !!!");
        failLabel.setVisible(false); // بالاول ببينش
        startPain.getChildren().add(failLabel);
        

        loginButton.setOnAction(e->{
        	LoginAction();
        });
        
        nameField.setOnKeyPressed(e -> { // معنى هذا السطر ان لما نكبس على زر في الكيبورد
            if (e.getCode() == KeyCode.ENTER) { // هذا الشرط بده يتأكد ان الزر اللي كبسنا عليه هو الانتر
            	LoginAction(); // وهان استدعيت الميثود تبعت لما اكبس على كبسة اللوج إن 
            }
        });

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
            	LoginAction();
            }
        });
        
        visiblePasswordField.setOnKeyPressed(e -> {
          if (e.getCode() == KeyCode.ENTER) {
        	  LoginAction();
          }
      });
        
        mainPane.setCenter(startPain);
	}

	
//===========================================================================================================================
	
	private void LoginAction() {
		
		
            String enteredUsername = nameField.getText().trim();
            // trim عشان لو في فراغات يشيلهن
            String enteredPassword = passwordField.getText().trim();
           

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                failLabel.setText("Please enter both username and password.");
                failLabel.setVisible(true);
                return; // هان بنوقف التنفيذ
            }
            
            
            boolean done = false;
            try {
                File file = new File("data/users.txt");
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                	// ما دام  ان في سطر في الملف 
                    String line = scanner.nextLine();// هان بنقرا السطر
                    
                    String[] parts = line.split(" "); // هان بنفصل الاسم عن الباسوورد

                    if (parts.length == 2) {
                    	// اذا كان عندي اسم وباسوورد سوي كذا وكذا 
                        User userFromFile = new User(parts[0], parts[1]);

                        if (enteredUsername.equals(userFromFile.getUsername()) &&
                            enteredPassword.equals(userFromFile.getPassword())) {
                        	done = true;
                            break;
                        }
                    }
                }

                scanner.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            if (done) {
            	failLabel.setVisible(false);
            	showCategories();
                
            } else {
            	failLabel.setText("The username or password is incorrect, try again !!!");
            	failLabel.setVisible(true);
            	
            }
       
	}
	
	
//==========================================================================================================================
	
	
	private HBox changePasswordVisibility() {
		  visiblePasswordField = new TextField();
			visiblePasswordField.setMaxWidth(300);
			visiblePasswordField.setMinHeight(40);
			visiblePasswordField.setFont(Font.font("System", FontWeight.BOLD, 20));
			visiblePasswordField.setStyle("-fx-text-fill: black; -fx-background-color: #fffafd; -fx-border-color: #f52a92; -fx-border-width: 3;");
			visiblePasswordField.setPromptText("password : ");
			visiblePasswordField.setManaged(false);
			visiblePasswordField.setVisible(false);

			ImageView eyeOpen = new ImageView(new Image(getClass().getResourceAsStream("/images/openeye.png")));
			eyeOpen.setFitWidth(30);
			eyeOpen.setFitHeight(30);

			ImageView eyeClosed = new ImageView(new Image(getClass().getResourceAsStream("/images/closeeye.png")));
			eyeClosed.setFitWidth(30);
			eyeClosed.setFitHeight(30);

			Button toggleVisibilityButton = new Button();
			toggleVisibilityButton.setGraphic(eyeClosed);
			toggleVisibilityButton.setStyle("-fx-background-color: transparent;");

			
			toggleVisibilityButton.setOnAction(e -> {
			    if (passwordField.isVisible()) {
			        visiblePasswordField.setText(passwordField.getText());
			        passwordField.setVisible(false);
			        passwordField.setManaged(false);
			        visiblePasswordField.setVisible(true);
			        visiblePasswordField.setManaged(true);
			        toggleVisibilityButton.setGraphic(eyeOpen);
			    } else {
			        passwordField.setText(visiblePasswordField.getText());
			        visiblePasswordField.setVisible(false);
			        visiblePasswordField.setManaged(false);
			        passwordField.setVisible(true);
			        passwordField.setManaged(true);
			        toggleVisibilityButton.setGraphic(eyeClosed);
			    }
			});

			visiblePasswordField.textProperty().addListener((obs, oldText, newText) -> {
			    if (visiblePasswordField.isVisible()) {
			        passwordField.setText(newText);
			    }
			});

			passwordField.textProperty().addListener((obs, oldText, newText) -> {
			    if (passwordField.isVisible()) {
			        visiblePasswordField.setText(newText);
			    }
			});

			HBox passwordBox = new HBox(2, passwordField, visiblePasswordField, toggleVisibilityButton);
			passwordBox.setAlignment(Pos.CENTER);
			HBox.setHgrow(passwordField, Priority.ALWAYS);
			HBox.setHgrow(visiblePasswordField, Priority.ALWAYS);
			
			return passwordBox;
	}
	
	
	
//=========================================================================================================================	
	
	
	
	private void showCategories() {
		mainiconsBox.setVisible(true);
	    titleLabel.setText("Select category");
	    titleLabel.setFont(Font.font("Century Schoolbook", FontWeight.BOLD, 60));

	    backButton.setVisible(true);
	    backButton.setOnAction(e -> showLogin());

	    GridPane gPane = new GridPane();
	    gPane.setVgap(30);
	    gPane.setHgap(30);
	    gPane.setAlignment(Pos.CENTER);

	    try {
	        Scanner scanner = new Scanner(new File("data/categories.txt"));

	        int row = 0;
	        int col = 0;

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] partStrings = line.split(" ");
	            if (partStrings.length != 2) continue;

	            String name = partStrings[0].trim();
	            String imageFileName = partStrings[1].trim();

	            Image image = new Image(getClass().getResourceAsStream("/images/" + imageFileName));

	         
	            ImageView imageView = new ImageView(image);
	            imageView.setFitWidth(160);
	            imageView.setFitHeight(160);
	            imageView.setPreserveRatio(true);
	            imageView.setSmooth(true);
	            imageView.setCache(true);
	            
	            Rectangle clip = new Rectangle(160, 160);
	            clip.setArcWidth(40); // خليها زي الـ border-radius
	            clip.setArcHeight(40);
	            imageView.setClip(clip);

	            StackPane imageContainer = new StackPane(imageView);
	            imageContainer.setPrefSize(160, 160);
	            imageContainer.setStyle("-fx-background-color: white;-fx-border-color: #FF1493;-fx-border-width: 3px;-fx-border-radius: 20px;-fx-background-radius: 20px;");
	            imageContainer.setAlignment(Pos.CENTER);

	            Button imButton = new Button();
	            imButton.setGraphic(imageContainer);
	            imButton.setStyle("-fx-background-color: transparent;");
	            imButton.setPrefSize(160, 160);
	            
	            
	            
	            imButton.setOnAction(e->{
	            	creatProducts(name);	
	            });

	            
	            Label label = new Label(name);
	            label.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-text-fill: #D5006D;");

	            VBox box = new VBox(10);
	            box.setAlignment(Pos.CENTER);
	            box.getChildren().addAll(imButton, label);

	            gPane.add(box, col, row);
	            col++;
	            if (col == 3) {
	                col = 0;
	                row++;
	            }
	        }

	        scanner.close();

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    mainPane.setCenter(gPane);
	}


//==========================================================================================================================	
	
	private void creatProducts(String categoryName) {
		mainiconsBox.setVisible(true);
		 backButton.setVisible(true);
		    backButton.setOnAction(e -> showCategories());
		
	    ArrayList<Product> products = new ArrayList<>();

	    try (Scanner scanner = new Scanner(new File("data/products.txt"))) {
	        while (scanner.hasNextLine()) {
	            String lineString = scanner.nextLine();
	            String[] data = lineString.split(" ");
	            if (data.length == 5) {
	                Product p = new Product(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4]);
	                // خطوة التحويل من نص ل دَبِل لان الاراي اللي عندي عبارة عن اراي من سترينجز والكونستر بيوخذ السعر ك دَبِل 
	                products.add(p);
	            }
	            // ملخص هاي اللوب ان من كل سطر في ملف التكست بدنا ننشئ اوبجيكت من نوع برودَكت 
	        }

	        ArrayList<String> categories = new ArrayList<>();
	        ArrayList<GridPane> grids = new ArrayList<>();
	        ArrayList<Integer> indices = new ArrayList<>();

	        for (Product product : products) {
	            if (!categories.contains(product.getCategory())) {
	                categories.add(product.getCategory());
	                GridPane grid = new GridPane();
	                grid.setAlignment(Pos.CENTER);
	               
	                grid.setHgap(10);
	                grid.setVgap(10);
	                grid.setPadding(new Insets(10));
	                grids.add(grid);
	                indices.add(0);
	            }
	            // هاي اللوب اللي فوق عشان ننشئ واجهة لكل فئة ونخزن هاي الواجهة اللي هي عبارة عن جرِد بين في الاراي ليست تبعت الجرِدز بين
	        }

	        for (Product product : products) {
	            String category2 = product.getCategory();
	            int i = categories.indexOf(category2);
	            GridPane gridPane = grids.get(i);
	           

	            int index = indices.get(i);
	            Button productButton = creatProB(product);
	            gridPane.add(productButton, index % 3, index / 3);
	            indices.set(i, index + 1);
	        }
	        
	        
	        // هذا الشرط اللي تحت والانتجر اللي معرف فوقه عشان لما اختار فئة محددة يعرف البرنامج انا ايش اخترت ويعرضلي المنتجات تبعت هاي الفئة بس
	        int selectedIndex = categories.indexOf(categoryName);
	        if (selectedIndex != -1) {
	        	 ScrollPane scrollPane = new ScrollPane();
	        	 scrollPane.setStyle(".scroll-bar:vertical { -fx-pref-width:30px;  }");
	        	 scrollPane.setStyle("-fx-background-color: white;");


	                scrollPane.setFitToWidth(true);
	                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
	                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	                scrollPane.setContent(grids.get(selectedIndex));

	            mainPane.setCenter(scrollPane);
	            
	            titleLabel.setText(categoryName); 
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	

//===========================================================================================================================	

	public Button creatProB(Product product) {
	    Image mImage = new Image("/images/"+product.getImageName());
	    ImageView imageView = new ImageView(mImage);
	    imageView.setFitHeight(300);
	    imageView.setFitWidth(280);

	    String productName = product.getName().replaceAll("-", " ");
	    Label l1 = new Label(productName);
	    l1.setFont(Font.font("Arial", FontWeight.NORMAL,16));
	    

	    Label l2 = new Label(product.getPrice() + " ₪");
	    l2.setFont(Font.font("Arial", FontWeight.NORMAL,16));

	   
	    Label l3 = new Label("Made in " + product.getOrigin());
	    l3.setFont(Font.font("Arial", FontWeight.NORMAL,16));

	    
	    Button cartButton = new Button();
	    cartButton.setPrefHeight(25);
	    cartButton.setPrefWidth(25);
	    Image heartImage = new Image("/images/cart.png");
	    ImageView heartView = new ImageView(heartImage);
	    heartView.setFitWidth(30); 
	    heartView.setFitHeight(30);
	    cartButton.setGraphic(heartView);
	    
	    cartButton.setOnAction(e->{
	    	cart.addProduct(product);
	    });
	    cartButton.setStyle("-fx-background-color: transparent;");

	    VBox pBox = new VBox(5);
	    pBox.getChildren().addAll(imageView, l1, l2, l3, cartButton);
	    pBox.setAlignment(Pos.CENTER);
	    mainPane.setCenter(pBox);

	    Button pButton = new Button();
	    pButton.setGraphic(pBox);
	    pButton.setStyle("-fx-background-color: transparent; -fx-border-color: #d5006d; -fx-border-width: 2; -fx-border-radius: 5;");

	    return pButton;
	}
	
//==========================================================================================================================	

	private void showCart() { 
		titleLabel.setText("Cart");
		
		VBox proVBox = new VBox(10);
		proVBox.setAlignment(Pos.CENTER);
		proVBox.setMaxWidth(600);
		ScrollPane scrollPane2 = new ScrollPane();// لاني استخدمت وحدة فوق فسميت هاي 2

		scrollPane2.setStyle("-fx-background-color: white;");


		scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
		scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane2.setContent(new StackPane(proVBox)); 
		scrollPane2.setFitToWidth(true);
		 Button confirmButton = new Button("Confirm purchase");
		 confirmButton.setFont(Font.font("Century Schoolbook", FontWeight.BOLD, 20));

	        confirmButton.setMaxWidth(270);
	        confirmButton.setMinHeight(50);
	        confirmButton.setStyle("-fx-background-color: #FF1493; -fx-text-fill: white;");
	        confirmButton.setOnAction(e->{
	        	cart.saveToFiles();
	        	
	        });
	        proVBox.getChildren().add(confirmButton);
		if(cart.getitems()!=null) {
			
	    	for(Product product :cart.getitems() ) {
	    		HBox productHBox = new HBox(25);
	    		productHBox.setStyle("-fx-border-color: #FF1493;");
	    		productHBox.setMaxWidth(600);
	    		
	    		Image productImage = new Image("/images/"+product.getImageName());
	    		 ImageView productImageView = new ImageView(productImage);
	    		 productImageView.setFitWidth(150); 
	    		 productImageView.setFitHeight(150);
	    		 
	    		 Button Delet = new Button();
	    	        Delet.setMaxWidth(20);
	    	        Delet.setMinHeight(20);
	    	        Image xImage=new Image("/images/delete.jpeg");
	    	        ImageView imxImageView=new ImageView(xImage);
	    	        imxImageView.setFitWidth(20); 
	    	        imxImageView.setFitHeight(20);
	    	        Delet.setGraphic(imxImageView);
	    	        Delet.setStyle("-fx-background-color: transparent;");
	    	        Delet.setAlignment(Pos.CENTER_RIGHT);


	    	       
	    		 
	    		 VBox detailVBox = new VBox(5);
	    		 detailVBox.setAlignment(Pos.CENTER);
	    		 Text productNameText = new Text(product.getName());
	    		 Text productPriceText = new Text(product.getPrice()+"");
	    		 productNameText.setFont(Font.font(18));
	    		 productPriceText.setFont(Font.font(18));
	    		 detailVBox.getChildren().addAll(productNameText,productPriceText);
	    		 
	    		 productHBox.getChildren().addAll(productImageView,detailVBox,Delet);
	    		 
	    		 Delet.setOnAction (e->{
	    		 cart.removeProduct(product);
	    		 showCart();
	    		 });
	    		 proVBox.getChildren().addAll(productHBox);
	    		 
	    		 }
	    }	



	    // نخلي السكرول يتمدد
	    VBox.setVgrow(scrollPane2, Priority.ALWAYS);
		mainPane.setCenter(scrollPane2);
		}
//=====================================================================================================================
	private void showInfo() { 
		titleLabel.setText("Informations about us ");
		VBox informationBox = new VBox(20);

		Region spacer = new Region();
		spacer.setMinHeight(55);
		
		Region spacer1 = new Region();
		spacer.setMinHeight(55);
		
		Region spacer2 = new Region();
		spacer.setMinHeight(55);
		informationBox.setAlignment(Pos.TOP_CENTER);
		
		
		Text layaName = new Text("Name : ** Laya Waleed Alshami ** ");
		layaName.setFont(Font.font("Century Schoolbook",FontWeight.BOLD, 40));
		layaName.setFill(Color.web("#a3105c"));
		
		Text layaEmail = new Text("Email : ** 202404582@bethlehem.edu **");
		layaEmail.setFont(Font.font("Century Schoolbook",FontWeight.BOLD, 40));
		layaEmail.setFill(Color.web("#a3105c"));
		
		//infoHBox.getChildren().addAll(layaName,layaEmail);
		
		Text DimaName = new Text("Name : ** Dima Sbeih ** ");
		DimaName.setFont(Font.font("Century Schoolbook",FontWeight.BOLD, 40));
		DimaName.setFill(Color.web("#a3105c"));
		
		Text DimaEmail = new Text("Email : ** 202404393@bethlehem.edu **");
		DimaEmail.setFont(Font.font("Century Schoolbook",FontWeight.BOLD, 40));
		DimaEmail.setFill(Color.web("#a3105c"));
		
		Text MaramName = new Text("Name : ** Maram Riyad Hajjaj ** ");
		MaramName.setFont(Font.font("Century Schoolbook",FontWeight.BOLD, 40));
		MaramName.setFill(Color.web("#a3105c"));
		
		Text MaramEmail = new Text("Email : ** 202404725@bethlehem.edu **");
		MaramEmail.setFont(Font.font("Century Schoolbook",FontWeight.BOLD, 40));
		MaramEmail.setFill(Color.web("#a3105c"));
		informationBox.getChildren().addAll(spacer,layaName,layaEmail,spacer1,DimaName,DimaEmail,spacer2,MaramName,MaramEmail);
		mainPane.setCenter(informationBox);


	}
	
//======================================================================================================================	
	
	public static void main(String[] args) {
		launch(args);
	}
}


