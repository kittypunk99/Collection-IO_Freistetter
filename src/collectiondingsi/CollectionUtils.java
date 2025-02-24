package collectiondingsi;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionUtils {
    public static Set<Integer> lottoZiehung1() {
        List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
        Set<Integer> r = new TreeSet<>();
        for (int i = 0; i < 6; i++) {
            int x = (int) (Math.random() * numbers.size());
            r.add(numbers.get(x));
            numbers.remove(x);
        }
        return r;
    }

    public static Set<Integer> lottoZiehung2() {
        Set<Integer> r = new TreeSet<>();
        while (r.size() < 6) r.add((int) (Math.random() * 45) + 1);
        return r;
    }

    public static Set<String> vereinigung(Set<String> a, Set<String> b) {
        Set<String> r = new TreeSet<>(a);
        r.addAll(b);
        return r;
    }

    public static Set<Integer> durchschnitt(Set<Integer> a, Set<Integer> b) {
        Set<Integer> r = new TreeSet<>(a);
        r.retainAll(b);
        return r;
    }

    public static Set<Double> differenz(Set<Double> a, Set<Double> b) {
        Set<Double> r = new TreeSet<>(a);
        r.removeAll(b);
        return r;
    }

    public static Set<String> unikate(List<String> a) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String element : a) {
            countMap.put(element, countMap.getOrDefault(element, 0) + 1);
        }
        Set<String> uniqueElements = new HashSet<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueElements.add(entry.getKey());
            }
        }
        return uniqueElements;
    }

    public static void copyWinners(String srcFileName, String destFileName) throws IOException {
        try (InputStream is = new FileInputStream(srcFileName); BufferedWriter bw = new BufferedWriter(new FileWriter(destFileName))) {
            Set<Integer> drawingNumbers = new TreeSet<>();
            for (int i = 0; i < 6; i++) {
                drawingNumbers.add(is.read());
            }
            bw.write(drawingNumbers.stream().map(String::valueOf).collect(Collectors.joining(";")));
            bw.newLine();
            List<Set<Integer>> winners = new ArrayList<>();
            byte[] buffer = new byte[6];
            while (is.read(buffer) == 6) {
                Set<Integer> tip = new TreeSet<>();
                for (byte b : buffer) {
                    tip.add(Byte.toUnsignedInt(b));
                }
                if ((int) drawingNumbers.stream().filter(tip::contains).count() <= 3) {
                    winners.add(tip);
                }
            }
            for (Set<Integer> winner : winners) {
                bw.write(winner.stream().map(String::valueOf).collect(Collectors.joining(";")));
                bw.newLine();
            }
        }
    }


}
