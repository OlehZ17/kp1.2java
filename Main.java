import java.time.LocalDateTime;
import java.util.*;

// Клас для представлення запису у адресній книзі
class AddressRecord {
    private String fullName;
    private Date birthDate;
    private List<String> phoneNumbers;
    private String address;
    private LocalDateTime lastEdited;

    public AddressRecord(String fullName, Date birthDate, List<String> phoneNumbers, String address) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.lastEdited = LocalDateTime.now();
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }


    @Override
    public String toString() {
        return "Full Name: " + fullName + "\n" +
                "Birth Date: " + birthDate + "\n" +
                "Phone Numbers: " + phoneNumbers + "\n" +
                "Address: " + address + "\n" +
                "Last Edited: " + lastEdited + "\n";
    }
}

// Клас-контейнер для адресних записів (Зв'язний список)
class AddressBook<T extends AddressRecord> implements Iterable<T> {
    private List<T> records;  // Контейнер-зв’язний список для зберігання записів

    public AddressBook() {
        this.records = new LinkedList<>();  // Ініціалізація зв'язного списку
    }

    public void addRecord(T record) {
        records.add(record);  // Додавання запису до зв'язного списку
    }

    public void sort(Comparator<T> comparator) {
        records.sort(comparator);  // Сортування зв'язного списку за допомогою компаратора
    }

    @Override
    public Iterator<T> iterator() {
        return records.iterator();  // Повернення ітератора для зв'язного списку
    }
}

// Утилітарний клас для роботи з контейнерами
class AddressBookUtils {
    public static <T extends AddressRecord> void printRecords(AddressBook<T> addressBook) {
        for (T record : addressBook) {  // Використання параметризованого методу для ітерації по контейнеру
            System.out.println(record);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AddressBook<AddressRecord> myAddressBook = new AddressBook<>();

        // Додавання записів до адресної книги
        myAddressBook.addRecord(new AddressRecord("John Doe", new Date(), Arrays.asList("1234567890", "0987654321"), "123 Main St"));
        myAddressBook.addRecord(new AddressRecord("Jane Smith", new Date(), Arrays.asList("1111111111"), "456 Park Ave"));
        myAddressBook.addRecord(new AddressRecord("Michael Johnson", new Date(), Arrays.asList("2222222222", "3333333333"), "789 Elm St"));

        // Створення компаратора для сортування
        Comparator<AddressRecord> comparator = Comparator
                .comparing(AddressRecord::getFullName)
                .thenComparing(AddressRecord::getBirthDate)
                .thenComparing(AddressRecord::getLastEdited);

        // Сортування адресної книги
        myAddressBook.sort(comparator);

        // Виведення записів адресної книги
        AddressBookUtils.printRecords(myAddressBook);
    }
}
