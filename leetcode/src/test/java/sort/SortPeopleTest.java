package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/sort-the-people">2418. 按身高排序</a>
 * @author xuzhou
 * @since 2023/9/12 13:51
 */
public class SortPeopleTest {

    public String[] sortPeople(String[] names, int[] heights) {
        People[] people = toPeople(names, heights);
        Arrays.sort(people, Comparator.reverseOrder());
        return toNames(people);
    }

    private People[] toPeople(String[] names, int[] heights) {
        int n = names.length;
        People[] people = new People[n];
        for (int i = 0; i < names.length; i++) {
            people[i] = new People(names[i], heights[i]);
        }
        return people;
    }

    private String[] toNames(People[] people) {
        int n = people.length;
        String[] names = new String[n];
        for (int i = 0; i < people.length; i++) {
            names[i] = people[i].name;
        }
        return names;
    }

    private static class People implements Comparable<People> {
        String name;
        int height;

        public People(String name, int height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public int compareTo(People o) {
            return height - o.height;
        }
    }
}
