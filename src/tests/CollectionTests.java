package tests;

import collectiondingsi.CollectionUtils;

import java.io.IOException;
import java.util.Set;

public class CollectionTests {
    public static void main(String[] args) {
        Set<Integer> lottoZiehung1 = CollectionUtils.lottoZiehung1();
        System.out.println("lottoZiehung1: " + lottoZiehung1);
        assert lottoZiehung1.size() == 6;
        assert lottoZiehung1.stream().allMatch(n -> n >= 1 && n <= 45);
        assert lottoZiehung1.stream().distinct().count() == 6;

        Set<Integer> lottoZiehung2 = CollectionUtils.lottoZiehung2();
        System.out.println("lottoZiehung2: " + lottoZiehung2);
        assert lottoZiehung2.size() == 6;
        assert lottoZiehung2.stream().allMatch(n -> n >= 1 && n <= 45);
        assert lottoZiehung2.stream().distinct().count() == 6;

        Set<String> vereinigung = CollectionUtils.vereinigung(Set.of("a", "b", "c"), Set.of("b", "c", "d"));
        System.out.println("vereinigung: " + vereinigung);
        assert vereinigung.equals(Set.of("a", "b", "c", "d"));

        Set<Integer> durchschnitt = CollectionUtils.durchschnitt(Set.of(1, 2, 3), Set.of(2, 3, 4));
        System.out.println("durchschnitt: " + durchschnitt);
        assert durchschnitt.equals(Set.of(2, 3));

        Set<Double> differenz = CollectionUtils.differenz(Set.of(1.0, 2.0, 3.0), Set.of(2.0, 3.0, 4.0));
        System.out.println("differenz: " + differenz);
        assert differenz.equals(Set.of(1.0));

        Set<String> unikate = CollectionUtils.unikate(java.util.List.of("a", "b", "a", "c", "b"));
        System.out.println("unikate: " + unikate);
        assert unikate.equals(Set.of("a", "b", "c"));

        try {
            CollectionUtils.copyWinners("ressources/lotto.dat", "outTests/winners.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
