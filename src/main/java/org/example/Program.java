package org.example;
import org.example.dao.ManufactureDAO;
import org.example.dao.PhoneDAO;
import org.example.domain.Manufacture;
import org.example.domain.Phone;
import java.util.List;

public class Program {
    public static PhoneDAO  phoneDAO= new PhoneDAO();
    public static ManufactureDAO manufactureDAO= new ManufactureDAO();

    public static void main(String[] args) {
        // thêm dữ liệu
        newData();
        // PhoneDAO
        //phoneOperations();

        //ManufactureDAO
        manufactureOperations();
    }

    public static void newData(){
        Manufacture m1= new Manufacture("Honda","India",10);
        manufactureDAO.add(m1);
        Manufacture m2= new Manufacture("Toyota","Koroe",11);
        manufactureDAO.add(m2);
        Manufacture m3= new Manufacture("Nokia","Japan",101);
        manufactureDAO.add(m3);
        Manufacture m4= new Manufacture("Samsung","ThaiLan",102);
        manufactureDAO.add(m4);
        Manufacture m5= new Manufacture("Apple","US",200);
        manufactureDAO.add(m5);
        Manufacture m6= new Manufacture("Apple1","US",201);
        manufactureDAO.add(m6);
        Manufacture m7= new Manufacture("Apple2","US",202);
        manufactureDAO.add(m7);

        Phone p = new Phone("HA",20,"red","VN",10);
        Phone p1 = new Phone("HB",21,"blue","india",11,m1);
        Phone p2 = new Phone("HC",50000000,"green","Koroe",12,m2);
        Phone p3 = new Phone("HD",80000000,"brown","china",12,m3);
        Phone p4 = new Phone("HE",85000000,"white","china",12,m4);
        Phone p5 = new Phone("HF",22,"yellow","china",12,m5);
        p.setManufacture(m1);
        phoneDAO.add(p);
        phoneDAO.add(p1);
        phoneDAO.add(p2);
        phoneDAO.add(p3);
        phoneDAO.add(p4);
        phoneDAO.add(p5);
    }

    public static void phoneOperations() {
        AddPhone();
        GetPhoneById();
        GetAllPhones();
        UpdatePhone();
        RemovePhoneById();
        RemovePhoneByObject();
        GetHighestPricePhone();
        GetPhonesSortedByCountry();
        HasPhoneAbove50Million();
        GetFirstPinkPhoneOver15Million();
    }
    public static void manufactureOperations() {
        AddManufacture();
        GetManufactureById();
        GetAllManufactures();
        UpdateManufacture();
        RemoveManufactureById();
        RemoveManufactureByObject();
        AllManufacturersHaveMoreThan100Employees();
        GetTotalEmployees();
        GetLastManufacturerBasedInUS();
    }

    public static void AddPhone() {
    System.out.println(" add phone ");
    Manufacture newMa= new Manufacture("Messi","VietNam",20);
    manufactureDAO.add(newMa);
    Phone phoneAdd = new Phone("iphone 16", 100000000, "Pink", "VN", 15,newMa);
    boolean added = phoneDAO.add(phoneAdd);
    System.out.println("Add Phone: " + added);
    }

    public static void GetPhoneById() {
        System.out.println(" Get Phone By ID ");
        Phone phoneById = phoneDAO.get(4);
        System.out.println("Phone by id: " + phoneById);
    }

    public static void GetAllPhones() {
        System.out.println(" Get All Phones ");
        List<Phone> allPhones = phoneDAO.getAll();
        for (Phone phone : allPhones) {
            System.out.println(phone);
        }
    }

    public static void UpdatePhone() {
        System.out.println(" Update Phone ");
        Phone phoneToUpdate = phoneDAO.get(2);
        if (phoneToUpdate != null) {
            phoneToUpdate.setPrice(30000);
            phoneToUpdate.setColor("newColor");
            phoneToUpdate.setName("NHA");
            phoneToUpdate.setCountry("Trung Quoc");
            boolean updated = phoneDAO.update(phoneToUpdate);
            System.out.println("Updated Phone: " + updated);
            System.out.println("Updated Phone Details: " + phoneDAO.get(phoneToUpdate.getId()));
        } else {
            System.out.println("Phone not found for update.");
        }
    }

    public static void RemovePhoneById() {
        System.out.println(" Remove Phone By ID ");
        boolean removedById = phoneDAO.remove(3);
        System.out.println("Removed Phone by ID: " + removedById);
    }

    public static void RemovePhoneByObject() {
        System.out.println(" Remove Phone By Object ");
        Phone phoneToRemove = phoneDAO.get(2);
        boolean removedByObject = phoneDAO.remove(phoneToRemove);
        System.out.println("Removed Phone by Object: " + removedByObject);
    }

    public static void GetHighestPricePhone() {
        System.out.println("Get Highest Price Phone ");
        Phone highestPricePhone = phoneDAO.getHighestPricePhone();
        System.out.println("Highest Price Phone: " + highestPricePhone);
    }

    public static void GetPhonesSortedByCountry() {
        System.out.println("Get Phones Sorted By Country ");
        List<Phone> sortedPhones = phoneDAO.getPhonesSortedByCountry();
        for (Phone phone : sortedPhones) {
            System.out.println(phone);
        }
    }

    public static void HasPhoneAbove50Million() {
        System.out.println("Has Phone Above 50 Million ");
        boolean hasExpensivePhone = phoneDAO.hasPhoneAbove50Million();
        System.out.println("Has Phone Above 50 Million: " + hasExpensivePhone);
    }

    public static void GetFirstPinkPhoneOver15Million() {
        System.out.println("Get First Pink Phone Over 15 Million ");
        Phone pinkPhone = phoneDAO.getFirstPinkPhoneOver15Million();
        System.out.println("First Pink Phone Over 15 Million: " + pinkPhone);
    }

    // ManufactureDAO

    public static void AddManufacture() {
        System.out.println(" Add Manufacture ");
        Manufacture Mnew = new Manufacture("Ronado", "VietNam", 200);
        boolean added = manufactureDAO.add(Mnew);
        System.out.println("Add Manufacture: " + added);
    }

    public static void GetManufactureById() {
        System.out.println(" Get Manufacture By ID ");
        Manufacture manufactureById = manufactureDAO.get(4);
        System.out.println("Manufacture by ID: " + manufactureById);
    }

    public static void GetAllManufactures() {
        System.out.println(" Get All Manufactures ");
        List<Manufacture> allManufactures = manufactureDAO.getAll();
        for (Manufacture manufacture : allManufactures) {
            System.out.println(manufacture);
        }
    }

    public static void UpdateManufacture() {
        System.out.println(" Update Manufacture ");
        Manufacture manufactureToUpdate = manufactureDAO.get(4);
        if (manufactureToUpdate != null) {
            manufactureToUpdate.setName("Redme no pro");
            manufactureToUpdate.setLocation("Trung Quoc");
            manufactureToUpdate.setEmployee(300);
            boolean updated = manufactureDAO.update(manufactureToUpdate);
            System.out.println("Updated Manufacture: " + updated);
            System.out.println("Updated Manufacture Details: " + manufactureDAO.get(manufactureToUpdate.getId()));
        } else {
            System.out.println("Manufacture not found for update.");
        }
    }

    public static void RemoveManufactureById() {
        System.out.println(" Remove Manufacture By ID");
        boolean removedById = manufactureDAO.remove(2);
        System.out.println("Removed Manufacture by ID: " + removedById);
    }

    public static void RemoveManufactureByObject() {
        System.out.println(" Remove Manufacture By Object");
        Manufacture manufactureToRemove = manufactureDAO.get(1);
        boolean removedByObject = manufactureDAO.remove(manufactureToRemove);
        System.out.println("Removed Manufacture by Object: " + removedByObject);
    }

    public static void AllManufacturersHaveMoreThan100Employees() {
        System.out.println(" All Manufacturers Have More Than 100 Employees");
        boolean result = manufactureDAO.allManufacturersHaveMoreThan100Employees();
        System.out.println("All Manufacturers Have More Than 100 Employees: " + result);
    }

    public static void GetTotalEmployees() {
        System.out.println(" Get Total Employees");
        int totalEmployees = manufactureDAO.getTotalEmployees();
        System.out.println("Total Employees: " + totalEmployees);
    }

    public static void GetLastManufacturerBasedInUS() {
        System.out.println("Get Last Manufacturer Based In US");
        try {
            Manufacture lastUSManufacturer = manufactureDAO.getLastManufacturerBasedInUS();
            System.out.println("Last Manufacturer Based in US: " + lastUSManufacturer);
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
