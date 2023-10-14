
//import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach ❌

    /*
     * List<Person> females = new ArrayList<>();
     * 
     * for (Person person : people) {
     * 
     * if (person.getGender().equals(Gender.FEMALE)) { females.add(person); } }
     * 
     * females.forEach(System.out::println);
     */

    // Declarative approach ✅
    // Filter
    List<Person> females = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
        .collect(Collectors.toList());

    females.forEach(System.out::println);

    // Sort
    List<Person> sorted = people.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());

    // sorted.forEach(System.out::println);

    // All match
    boolean allMatch = people.stream().allMatch(person -> person.getAge() > 8);
    System.out.println(allMatch);

    // Any match
    boolean anyMatch = people.stream().anyMatch(person -> person.getAge() > 8);
    System.out.println(anyMatch);

    // None match
    boolean noneMatch = people.stream().noneMatch(person -> person.getName().equals("Fulanito"));
    System.out.println(noneMatch);

    // Max
    // Optional<Person> max =
    people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(person -> {
      System.out.println(person);
    });
    // System.out.println(max);

    // Min
    people.stream().min(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);

    // Group
    Map<Gender, List<Person>> groupoByGender = people.stream().collect(Collectors.groupingBy(Person::getGender));
    // System.out.println(groupoByGender);
    groupoByGender.forEach((gender, people1) -> {
      System.out.println(gender);
      people1.forEach(System.out::println);
      System.out.println();
    });

    Optional<String> mujerMasMAyor = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
        .max(Comparator.comparing(Person::getAge)).map(Person::getName);

    mujerMasMAyor.ifPresent(System.out::println);

  }

  private static List<Person> getPeople() {
    return List.of(new Person("Antonio", 20, Gender.MALE), new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE), new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE), new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE));
  }

}
