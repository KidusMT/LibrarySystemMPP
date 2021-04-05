# Project Build Setups

## VM Options for JavaFX:
```bash
--module-path /path/to/javafx-sdk/lib --add-modules=javafx.controls,javafx.fxml
```

## CMD command for running the jar file in the release for this app (java 16 with javafx 16 for running)
```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar LibrarySystemMPP.jar
```

## Java SDK and JavaFx Versions
 - Java SDK: `jdk-15.0.2`
 - JavaFX: `javafx-sdk-15.0.2`

# LibrarySystemMPP
Demo  project for CS401-2021-03E-04C(RM)

## Members: Group-2
- Anteneh Ashenafi
- Eskindir Abebe
- Abenezer Sintayehu
- Kidus Tekeste

## User Auth Roles:
- Super Role:
   - username: `super`
   - password: `super`
- Admin Role:
   - username: `admin`
   - password: `admin`
- Librarian Role:
   - username: `library`
   - password: `library`

## Documentation
### Use case Diagram
![image](https://user-images.githubusercontent.com/18373774/113563589-91b00b80-95cd-11eb-9b58-7c3df0ab195d.png)

### Class Diagram
![image](https://user-images.githubusercontent.com/18373774/113563612-a1c7eb00-95cd-11eb-9fa1-9fe2900d3bbf.png)

### Sequence Diagram
- Sequence Diagram for `Checkout Book`

![image](https://user-images.githubusercontent.com/18373774/113563646-adb3ad00-95cd-11eb-8830-d3b226aff025.png)

- Sequence Diagram for `Add a copy of existing book`

![image](https://user-images.githubusercontent.com/18373774/113563672-b86e4200-95cd-11eb-9250-6ff29193c165.png)

## Screenshots of the Application
 - Login Screen
 ![image](https://user-images.githubusercontent.com/18373774/113563733-da67c480-95cd-11eb-8d4c-874a78cfb15b.png)
 
 - Librarian User: Dashboard ( `checkout list` | `checkout entry list` )
 ![image](https://user-images.githubusercontent.com/18373774/113563842-05521880-95ce-11eb-91c5-a1d4b63cc258.png)

 - Librarian User: `Add Checkout` dialog Screen
 ![image](https://user-images.githubusercontent.com/18373774/113563955-2ca8e580-95ce-11eb-9e43-f3436f9e3485.png)
 
 - Librarian User: `Update Checkout` dialog Screen
 ![image](https://user-images.githubusercontent.com/18373774/113564111-637efb80-95ce-11eb-9a26-621df0f804f7.png)
 
 - Admin User: User Dashboard
 ![image](https://user-images.githubusercontent.com/18373774/113564339-d6887200-95ce-11eb-918a-e0e2b7e56068.png)

 - Admin User: Book Detail Screen
 ![image](https://user-images.githubusercontent.com/18373774/113564412-f61f9a80-95ce-11eb-9e61-c328664493fb.png)

 - Admin User: Create Book Screen
 ![image](https://user-images.githubusercontent.com/18373774/113564465-10f20f00-95cf-11eb-9718-81243317b4c6.png)

 - Admin User: Create Book -> `Add Author` Screen
 ![image](https://user-images.githubusercontent.com/18373774/113564642-557daa80-95cf-11eb-9ad1-c6135f0e72a0.png)

 - Admin User: `Members List` Screen
 ![image](https://user-images.githubusercontent.com/18373774/113564741-77772d00-95cf-11eb-97bc-c77a6811d08e.png)
 
 - Admin User: `Update Member` Screen
 ![image](https://user-images.githubusercontent.com/18373774/113564929-c7ee8a80-95cf-11eb-911e-e0d52a129d53.png)
 
 - Admin User: `Alert Dialog` For Member Deletion Prompt
 ![image](https://user-images.githubusercontent.com/18373774/113565028-f1a7b180-95cf-11eb-8e22-48eea7717d1b.png)
 
 - Admin User: `Create Member` Screen
 ![image](https://user-images.githubusercontent.com/18373774/113565091-0e43e980-95d0-11eb-890a-76bf3261ff71.png)
 
 - Super User: `Home Dashboard` Screen With All The Features On Menu
 ![image](https://user-images.githubusercontent.com/18373774/113565215-42b7a580-95d0-11eb-86b7-e2cfae134801.png)






