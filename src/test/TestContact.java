package test;

import io.SerializeFile;
import model.Contact;

import java.util.*;

public class TestContact {
    static HashMap<Integer, Contact>data=new HashMap<Integer,Contact>();
    public static void menu() {
        System.out.println("1.Add");
        System.out.println("2.Print");
        System.out.println("3.Update");
        System.out.println("4.Delete");
        System.out.println("5.Find");
        System.out.println("6.Sort");
        System.out.println("7.Save File");
        System.out.println("8.Read File");
        System.out.println("9.Exit");
        System.out.println("Your Choice: ");
        int n = new Scanner(System.in).nextInt();
        switch (n) {
            case 1:
                add();
                break;
            case 2:
                print();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            case 5:
                find();
                break;
            case 6:
                sort();
                break;
            case 7:
                saveFile();
                break;
            case 8:
                readFile();
                break;
            case 9:
                System.err.println("Bye!");
                System.exit(0);
                break;

        }
    }

    private static void add(){
        System.out.println("Enter id: ");
        int id = new Scanner(System.in).nextInt();
        System.out.println("Enter name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter phone: ");
        String phone=new Scanner(System.in).nextLine();

        Contact c = new Contact(id,name,phone);
        if (data.containsKey(c.getId())==false) {
            data.put(c.getId(),c);
        }
    }
    private static void print(){
        System.out.println("List: ");
        for (Map.Entry<Integer,Contact>item : data.entrySet() ){
            System.out.println(item.getValue());
        }
    }
    private static void update(){
        System.out.println("Enter id: ");
        int id =new Scanner(System.in).nextInt();
        if(data.containsKey(id)){
            System.out.println("Enter name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter phone: ");
            String phone=new Scanner(System.in).nextLine();
            Contact c = new Contact(id,name,phone);
            data.put(id,c);
        }
    }
    private static void delete(){
        System.out.println("Enter id: ");
        int id=new Scanner(System.in).nextInt();
        if(data.containsKey(id)){
            data.remove(id);
        }
        else{
            System.out.println("Cannot find id "+id+" to delete");
        }
    }
    private static void find(){
        System.out.println("Enter phone: ");
        String phone =new Scanner(System.in).nextLine();
        for(Map.Entry<Integer,Contact>item:data.entrySet()){
            if(item.getValue().getPhone().startsWith(phone))
                System.out.println(item.getValue());

        }
    }
    private static void sort() {
        System.out.println("Sort by: ");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Phone");
        System.out.print("Your choice: ");
        int choice = new Scanner(System.in).nextInt();

        List<Map.Entry<Integer, Contact>> contactList = new ArrayList<>(data.entrySet());

        switch (choice) {
            case 1:
                sortByCriteria(contactList, Comparator.comparing(Map.Entry::getKey));
                for (Map.Entry<Integer, Contact> entry : contactList) {
                    System.out.println("Id: " + entry.getKey() +
                            ", Name: " + entry.getValue().getName() +
                            ", Phone: " + entry.getValue().getPhone());
                }
                break;
            case 2:
                sortByCriteria(contactList, Comparator.comparing(entry -> entry.getValue().getName()));
                for (Map.Entry<Integer, Contact> entry : contactList) {
                    System.out.println("Name: " + entry.getValue().getName() +
                            ", Id: " + entry.getKey() +
                            ", Phone: " + entry.getValue().getPhone());
                }
                break;
            case 3:
                sortByCriteria(contactList, Comparator.comparing(entry -> entry.getValue().getPhone()));
                for (Map.Entry<Integer, Contact> entry : contactList) {
                    System.out.println("Phone: " + entry.getValue().getPhone() +
                            ", Name: " + entry.getValue().getName() +
                            ", Id: " + entry.getKey());
                }
                break;
            default:
                System.err.println("Invalid choice for sorting.");
                return;
        }
    }

    private static void sortByCriteria(List<Map.Entry<Integer, Contact>> contactList,
                                       Comparator<Map.Entry<Integer, Contact>> comparator) {
        Collections.sort(contactList, comparator);

    }




    private static void saveFile(){
        SerializeFile.saveFile(data,"D://data.txt");
    }
    private static void readFile(){
        data=SerializeFile.readFile("D://data.txt");
    }
    public static void main(String[] args) {
        while (true){
            menu();
        }
    }
}