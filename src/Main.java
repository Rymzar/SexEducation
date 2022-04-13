import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.print(count);

        List<String> conscript = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(p -> p.getSex() == Sex.MAN)
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(conscript);

        List<String> works = persons.stream()
                .filter(q -> q.getEducation() == Education.HIGHER)
                .filter(q -> q.getSex() == Sex.MAN)
                .filter(q -> q.getSex() == Sex.MAN && q.getAge() >= 18 && q.getAge() <= 65)
                .filter(q -> q.getSex() == Sex.WOMAN)
                .filter(q -> q.getSex() == Sex.WOMAN && q.getAge() >= 18 && q.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getName))
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(works);
    }
}
